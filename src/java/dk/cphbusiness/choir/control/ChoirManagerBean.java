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
import java.util.Collection;
import javax.ejb.Stateless;

/**
 *
 * @author
 * kasper
 */
@Stateless
public class ChoirManagerBean implements ChoirManager{

    @Override
    public MemberAuthentication login(String email, String password) throws AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<RoleSummary> listRoles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MemberSummary> listMembers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MemberSummary> listMembersByRole(String roleCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MemberSummary> listMembersByVoices(int voiceCodes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MemberDetail findMember(long id) throws NoSuchMemberException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MemberDetail saveMember(MemberAuthentication user, MemberDetail member) throws NoSuchMemberException, AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
