package dk.cphbusiness.choir.control;

import dk.cphbusiness.choir.contract.ChoirManager;
import dk.cphbusiness.choir.contract.dto.ArtistDetail;
import dk.cphbusiness.choir.contract.dto.ArtistSummary;
import dk.cphbusiness.choir.contract.dto.MaterialDetail;
import dk.cphbusiness.choir.contract.dto.MaterialSummary;
import dk.cphbusiness.choir.contract.dto.MemberAuthentication;
import dk.cphbusiness.choir.contract.dto.MemberDetail;
import dk.cphbusiness.choir.contract.dto.MemberSummary;
import dk.cphbusiness.choir.contract.dto.MusicDetail;
import dk.cphbusiness.choir.contract.dto.MusicSummary;
import dk.cphbusiness.choir.contract.dto.RoleSummary;
import dk.cphbusiness.choir.contract.dto.VoiceSummary;
import dk.cphbusiness.choir.contract.eto.AuthenticationException;
import dk.cphbusiness.choir.contract.eto.NoSuchArtistException;
import dk.cphbusiness.choir.contract.eto.NoSuchMaterialException;
import dk.cphbusiness.choir.contract.eto.NoSuchMemberException;
import dk.cphbusiness.choir.contract.eto.NoSuchMusicException;
import dk.cphbusiness.choir.model.ChoirMember;
import dk.cphbusiness.choir.model.ChoirRole;
import dk.cphbusiness.choir.model.Voice;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author
 * kasper
 */
@Stateless
public class ChoirManagerBean implements ChoirManager{

    @Override
    public MemberAuthentication login(String email, String password) throws AuthenticationException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChoirBackendPU");
        EntityManager em = emf.createEntityManager();
        ChoirMember member = null;
        try{
        member = (ChoirMember)em.createQuery("SELECT m FROM ChoirMember m WHERE m.email= :email AND m.password=:password")
                                                             .setParameter("email", email)
                                                             .setParameter("password", password)
                                                             .getSingleResult();
        }catch(Exception e){
         throw new AuthenticationException("failed login");
        }
        MemberAuthentication authentication = new MemberAuthentication(member.getId(),member.getEmail() );
        for(ChoirRole role : member.getChoirRoles()){
            authentication.addRoleCode(role.getCode());
        }
        
        return authentication;
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) throws AuthenticationException {
        
    }

    @Override
    public Collection<VoiceSummary> listVoices() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChoirBackendPU");
        EntityManager em = emf.createEntityManager();
        Collection<VoiceSummary> voices = new ArrayList<VoiceSummary>();
        for(Voice voice : (ArrayList<Voice>)em.createNamedQuery("Voice.findAll").getResultList())
        {
            voices.add(ChoirAssembler.createVoiceSummary(voice));
        }
        return voices;
    }

    @Override
    public Collection<RoleSummary> listRoles() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChoirBackendPU");
        EntityManager em = emf.createEntityManager();
        Collection<RoleSummary> roles = new ArrayList<RoleSummary>();
        for(ChoirRole role : (ArrayList<ChoirRole>)em.createNamedQuery("ChoirRole.findAll").getResultList())
        {
            roles.add(ChoirAssembler.createRoleSummary(role));
        }
        return roles;
    }

    @Override
    public Collection<MemberSummary> listMembers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChoirBackendPU");
        EntityManager em = emf.createEntityManager();
        Collection<MemberSummary> members = new ArrayList<MemberSummary>();
        for(ChoirMember member : (ArrayList<ChoirMember>)em.createNamedQuery("ChoirMember.findAll").getResultList())
        {
            members.add(ChoirAssembler.createMemberSummary(member));
        }
        return members;
    }

    @Override
    public Collection<MemberSummary> listMembersByRole(String roleCode) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChoirBackendPU");
        EntityManager em = emf.createEntityManager();
        ChoirRole role = em.find(ChoirRole.class, roleCode);
                System.out.println(role.getCode());
        Collection<MemberSummary> members = new ArrayList<MemberSummary>();
        for(ChoirMember member : role.getMembers()){
            members.add(ChoirAssembler.createMemberSummary(member));
        }
        
        return members;
    }

    @Override
    public Collection<MemberSummary> listMembersByVoices(int voiceCodes) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChoirBackendPU");
        EntityManager em = emf.createEntityManager();
        Voice voice = em.find(Voice.class,voiceCodes);
        Collection<MemberSummary> members = new ArrayList<MemberSummary>();
        for(ChoirMember member : voice.getMembers()){
            members.add(ChoirAssembler.createMemberSummary(member));
        }
        return members;
    }

    @Override
    public MemberDetail findMember(long id) throws NoSuchMemberException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChoirBackendPU");
        EntityManager em = emf.createEntityManager();
        MemberDetail member = ChoirAssembler.createMemberDetail(em.find(ChoirMember.class, id));
        return member;  
    }

    @Override
    public MemberDetail saveMember(MemberAuthentication user, MemberDetail member) throws NoSuchMemberException, AuthenticationException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChoirBackendPU");
        EntityManager em = emf.createEntityManager();
        
        //Checks if user has permission to edit this member. Only allowed if user is admin or editing themselves.
        if(user.getId() == member.getId() || user.isAdministrator()){
            em.getTransaction().begin();
            ChoirMember choirMember = new ChoirMember();
            choirMember.setFirstName(member.getFirstName());
            choirMember.setLastName(member.getLastName());
            choirMember.setCity(member.getCity());
            choirMember.setEmail(member.getEmail());
            choirMember.setDateOfBirth(member.getDateOfBirth());
            choirMember.setPhone(member.getPhone());
            choirMember.setId((int)member.getId());
            
            //Adds roles for the Choir Member
            for(RoleSummary role : member.getRoles()){
                ChoirRole cRole = new ChoirRole(role.getCode());
                cRole.setName(role.getName());  
                choirMember.getChoirRoles().add(cRole);
            }
            
            ChoirMember dbMember = em.find(ChoirMember.class, member.getId());  //Selects the member in the DB which has the same ID as the MemberDetail object.
            
            if(dbMember != null)
                em.refresh(choirMember);            //Updates if member already exists in DB
            else
                em.persist(choirMember);            //Creates new member if it doesn't already exist in DB
            
            em.getTransaction().commit();
            em.close();
        }
        else{
            throw new AuthenticationException("User has insufficient rights.");
        }
        
        return member;
    }

    @Override
    public Collection<MaterialSummary> listMaterials() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MaterialSummary> listMaterialsByVoices(int voiceCodes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MaterialSummary> listMaterialsByMusic(long musicId) throws NoSuchMusicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaterialDetail findMaterial(long id) throws NoSuchMaterialException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaterialDetail saveMaterial(MemberAuthentication user, MaterialDetail material) throws NoSuchMaterialException, AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MusicSummary> listMusic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MusicSummary> listMusicByComposer(long composerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MusicDetail findMusic(long id) throws NoSuchMusicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MusicDetail saveMusic(MemberAuthentication user, MusicDetail music) throws NoSuchMusicException, AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<ArtistSummary> listArtists() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<ArtistSummary> listArtistsByPattern(String pattern) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArtistDetail findArtist(long id) throws NoSuchArtistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArtistDetail saveArtist(MemberAuthentication user, ArtistDetail artist) throws NoSuchArtistException, AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
