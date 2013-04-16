/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.choir.control;

import dk.cphbusiness.choir.contract.dto.MemberDetail;
import dk.cphbusiness.choir.contract.dto.RoleSummary;
import dk.cphbusiness.choir.contract.dto.VoiceSummary;
import dk.cphbusiness.choir.model.ChoirMember;
import dk.cphbusiness.choir.model.ChoirRole;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Kasper-PC
 */
public class ChoirAssembler {
    
    public MemberDetail createMemberDetail(ChoirMember member){
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
    

}
