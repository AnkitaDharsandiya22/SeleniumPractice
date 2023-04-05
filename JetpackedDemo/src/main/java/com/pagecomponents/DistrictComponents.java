package com.pagecomponents;

public enum DistrictComponents {
    ROCKETSHIPPUBLICSCHOOLS("Rocketship Public Schools"),
    REDBRIDGE("Redbridge"),
    ROMERO("Romero Academy at Resurrection"),
    KIPPDC("KIPP DC"),
    ACECHARTERSCHOOLS("Ace Charter Schools"),
    BRILLACOLLEGEPREPCHARTERSCHOOL("Brilla College Prep Charter School");
    private final String district;

    public String getDistrict() {
        return district;
    }

    DistrictComponents(String district) {
        this.district=district;
    }

}
