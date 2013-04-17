package dk.cphbusiness.choir.control;

import dk.cphbusiness.choir.contract.dto.MemberDetail;
import dk.cphbusiness.choir.contract.dto.MemberSummary;
import dk.cphbusiness.choir.contract.dto.RoleSummary;
import dk.cphbusiness.choir.contract.dto.VoiceSummary;
import dk.cphbusiness.choir.model.ChoirMember;
import dk.cphbusiness.choir.model.ChoirRole;
import dk.cphbusiness.choir.model.Voice;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Kasper-PC
 */
public class ChoirAssembler {
    
    public static MemberDetail createMemberDetail(ChoirMember member){
        Collection<RoleSummary> roles = new ArrayList<RoleSummary>();
        for(ChoirRole role : member.getChoirRoles()){
            roles.add(new RoleSummary(role.getCode(), role.getName()));
        }
        return new MemberDetail(
                    (long)member.getId(),
                    member.getFirstName(),
                    member.getLastName(),
                    "",
                    false,
                    false,
                    member.getDateOfBirth(),
                    new VoiceSummary(member.getVoice().getCode(),member.getVoice().getName()),
                    roles,
                    member.getStreet(),
                    member.getZipcode(),
                    member.getCity(),
                    member.getEmail(),
                    member.getPhone()
                );
    }
    
    public static MemberSummary createMemberSummary(ChoirMember member){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return new MemberSummary(
                (long)member.getId(),
                member.getVoice().getName(),
                member.getFirstName() + " " + member.getLastName(),
                "",
                member.getStreet() + ", " + member.getZipcode(),
                member.getEmail(),
                member.getPhone(),
                formatter.format(member.getDateOfBirth()),
                true
                );
    }
    
    public static VoiceSummary createVoiceSummary(Voice voice)
    {
        return new VoiceSummary(voice.getCode(), voice.getName());
    }
    
    public static RoleSummary createRoleSummary(ChoirRole role)
    {
        return new RoleSummary(role.getCode(), role.getName());
    }

}
