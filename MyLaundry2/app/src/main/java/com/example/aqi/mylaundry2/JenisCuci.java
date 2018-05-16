package com.example.aqi.mylaundry2;


public class JenisCuci {
    private String jenis;
    private String cucikeringsetrika;
    private String cucikering;
    private String setrikasaja;

    public JenisCuci(String jenis, String cucikeringsetrika, String cucikering, String setrikasaja) {
        this.jenis = jenis;
        this.cucikeringsetrika = cucikeringsetrika;
        this.cucikering = cucikering;
        this.setrikasaja = setrikasaja;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getCucikeringsetrika() {
        return cucikeringsetrika;
    }

    public void setCucikeringsetrika(String cucikeringsetrika) {
        this.cucikeringsetrika = cucikeringsetrika;
    }

    public String getCucikering() {
        return cucikering;
    }

    public void setCucikering(String cucikering) {
        this.cucikering = cucikering;
    }

    public String getSetrikasaja() {
        return setrikasaja;
    }

    public void setSetrikasaja(String setrikasaja) {
        this.setrikasaja = setrikasaja;
    }
}
