/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.choir.control;

import dk.cphbusiness.choir.contract.dto.MemberDetail;
import dk.cphbusiness.choir.contract.dto.VoiceSummary;
import dk.cphbusiness.choir.model.ChoirMember;

/**
 *
 * @author Kasper-PC
 */
public class ChoirAssembler {
    
    public MemberDetail createMemberDetail(ChoirMember member){
        return new MemberDetail(
                    member.getId(),
                    member.getFirstName(),
                    member.getLastName(),
                    null,
                    false,
                    false,
                    date,
                    new VoiceSummary(member.g),
                    
                   
                );
    }
    
    public VoiceSummary createVoiceSummary()
    {
        
    }
}
