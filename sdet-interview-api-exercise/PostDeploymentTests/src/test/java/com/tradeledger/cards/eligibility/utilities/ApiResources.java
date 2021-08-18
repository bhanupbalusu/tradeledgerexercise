package com.tradeledger.cards.eligibility.utilities;

public enum ApiResources {

    CheckEligibilityAPI("/eligibility/check");

    private String resource;

    ApiResources(String resource){
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
