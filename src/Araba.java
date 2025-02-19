import java.time.LocalTime;

class Araba {
    Rastgele rast = new Rastgele();
    private final String plaka;
    private final String marka;
    private final String tip;
    private LocalTime girisSaati ;
    private LocalTime kalmaSuresi;
    private LocalTime cikisSaati;
    private String yer;
    private final Boolean otoYikama;
    private int odenen;


    public Araba(LocalTime girisSaati, String aracyeri) {
        this.plaka = rast.setRplaka();
        this.tip = rast.setRtip();
        this.marka = rast.setRmarka(tip);
        this.girisSaati = girisSaati;
        this.kalmaSuresi = rast.setRkalmaSuresi(girisSaati);
        this.cikisSaati = girisSaati.plusHours(kalmaSuresi.getHour()).plusMinutes(kalmaSuresi.getMinute());
        this.yer = aracyeri;
        this.otoYikama=rast.setRyikama();
    }

    @Override
    public String toString() {
        String yikama =otoYikama ? "Evet":"Hayır";
        return "Araç Bilgileri={" +
                "Plaka='" + plaka + '\'' +
                ", Marka='" + marka + '\'' +
                ", Tip='" + tip + '\'' +
                ", Giriş Saati=" + girisSaati +
                ", Kalma Süresi=" + kalmaSuresi +
                ", Çıkış Saati=" + cikisSaati +
                ", Konumu=" + yer+
                ", Ödenen Tutar=" + odenen +
                ", Yıkama Yapıldı Mı=" + yikama +
                '}';
    }

    public void setOdenen(int odenen) {
        this.odenen = odenen;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }

    public void setSaatler(LocalTime girisSaati) {
        this.girisSaati = girisSaati;
        this.kalmaSuresi = rast.setRkalmaSuresi(girisSaati);
        this.cikisSaati = girisSaati.plusHours(kalmaSuresi.getHour()).plusMinutes(kalmaSuresi.getMinute());
    }

    public String getTip() {
        return tip;
    }

    public String getYer() {
        return yer;
    }

    public LocalTime getCikisSaati() {
        return cikisSaati;
    }

    public LocalTime getKalmaSuresi() {
        return kalmaSuresi;
    }

    public String getPlaka() {
        return plaka;
    }

    public int getOtoYikama() {
        return otoYikama ? 1 : 0;
    }

}