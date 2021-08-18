package com.tradeledger.cards.eligibility.utilities;

import com.tradeledger.cards.eligibility.pojo.models.request.ApplicantDetails;
import com.tradeledger.cards.eligibility.pojo.models.request.InvalidDataTypeApplicantDetails;
import com.tradeledger.cards.eligibility.pojo.models.request.InvalidKeyApplicantDetails;

public class TestDataBuild {
    public ApplicantDetails applicantDetailsPayload(String name, String email, String address) {

        String applicantName = Utility.handleTrailingSpaceAndNullCheck(name);
        String applicantEmail = Utility.handleTrailingSpaceAndNullCheck(email);
        String applicantAddress = Utility.handleTrailingSpaceAndNullCheck(address);

        ApplicantDetails applicantDetails = new ApplicantDetails();
        applicantDetails.setName(applicantName);
        applicantDetails.setEmail(applicantEmail);
        applicantDetails.setAddress(applicantAddress);
        return applicantDetails;
    }

    public InvalidDataTypeApplicantDetails invalidDataTypeApplicantDetailsPayload(String name, String email, String address) {

        int applicantName = Integer.parseInt(Utility.handleTrailingSpaceAndNullCheck(name));
        int applicantEmail = Integer.parseInt(Utility.handleTrailingSpaceAndNullCheck(email));
        int applicantAddress = Integer.parseInt(Utility.handleTrailingSpaceAndNullCheck(address));

        InvalidDataTypeApplicantDetails invalidDataTypeApplicantDetails = new InvalidDataTypeApplicantDetails();
        invalidDataTypeApplicantDetails.setName(applicantName);
        invalidDataTypeApplicantDetails.setEmail(applicantEmail);
        invalidDataTypeApplicantDetails.setAddress(applicantAddress);
        return invalidDataTypeApplicantDetails;
    }

    public InvalidKeyApplicantDetails invalidKeyApplicantDetailsPayload(String username, String email, String address) {

        String applicantName = Utility.handleTrailingSpaceAndNullCheck(username);
        String applicantEmail = Utility.handleTrailingSpaceAndNullCheck(email);
        String applicantAddress = Utility.handleTrailingSpaceAndNullCheck(address);

        InvalidKeyApplicantDetails invalidKeyApplicantDetails = new InvalidKeyApplicantDetails();
        invalidKeyApplicantDetails.setUsername(applicantName);
        invalidKeyApplicantDetails.setEmail(applicantEmail);
        invalidKeyApplicantDetails.setAddress(applicantAddress);
        return invalidKeyApplicantDetails;
    }
}
