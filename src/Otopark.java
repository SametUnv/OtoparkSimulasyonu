import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

class Otopark {
    private final ArrayList<String> yerler = new ArrayList<>();
    private int gunlukCiro;
    private int toplamCiro;
    private int toplamKar;
    private int yikananAracSayisi=0;
    private final int[] gelenGunluk={0,0,0,0};
    private final int[] gelenToplam={0,0,0,0};


    Random random = new Random();

    public void olusturYer() {
        yerler.clear();
        String[] harfler = {"A", "B", "C"};
        for (String harf : harfler) {
            for (int i = 1; i <= 5; i++) {
                yerler.add(harf + i);
            }
        }
    }
    public void gunlukSifirla(){
        this.gelenGunluk[0]=0;
        this.gelenGunluk[1]=0;
        this.gelenGunluk[2]=0;
        this.gelenGunluk[3]=0;
        this.gunlukCiro = 0;
        this.yikananAracSayisi=0;
    }

    public int odemeAl(LocalTime kalmaSuresi, int aracKodu,int yikama){
        if(yikama==1){
            yikananAracSayisi++;}
        int alinanOdeme;
        int []tipFiyati=getFiyatlandirma(aracKodu);
        setGelenGunluk(aracKodu);
        if( !kalmaSuresi.isBefore(LocalTime.of(0,0)) && !kalmaSuresi.isAfter(LocalTime.of(1,0))) {
            alinanOdeme=tipFiyati[0]+(yikama*100);
        }
        else if( !kalmaSuresi.isBefore(LocalTime.of(1,0)) && !kalmaSuresi.isAfter(LocalTime.of(2,0))) {
            alinanOdeme=tipFiyati[1]+(yikama*100);
        }
        else if( !kalmaSuresi.isBefore(LocalTime.of(2,0)) && !kalmaSuresi.isAfter(LocalTime.of(3,0))) {
            alinanOdeme=tipFiyati[2]+(yikama*100);
        }
        else if( !kalmaSuresi.isBefore(LocalTime.of(3,0)) && !kalmaSuresi.isAfter(LocalTime.of(5,0))) {
            alinanOdeme=tipFiyati[3]+(yikama*100);
        }else{
            alinanOdeme=kalmaSuresi.getHour()*tipFiyati[4]+(yikama*70);
        }
        this.gunlukCiro+= alinanOdeme;
        return alinanOdeme;
    }

    public int hizmetMaliyeti(){
        return 800+(yikananAracSayisi*3);
    }

    public String setDoldurYer() {
        int secim = random.nextInt(yerler.size());
        return yerler.remove(secim);
    }


    public void setBosaltYer(String eklenen) {
        yerler.add(eklenen);
        Collections.sort(yerler);
    }

    public void setGelenGunluk(int aracKodu) {
        this.gelenGunluk[aracKodu]++;
    }

    public void setGelenToplam() {
        this.gelenToplam[0]+=gelenGunluk[0];
        this.gelenToplam[1]+=gelenGunluk[1];
        this.gelenToplam[2]+=gelenGunluk[2];
        this.gelenToplam[3]+=gelenGunluk[3];
    }


    public void setToplamCiro(int toplamCiro) {
        this.toplamCiro+= toplamCiro;
    }

    public void setToplamKar() {
        this.toplamKar +=gunlukCiro-hizmetMaliyeti();
    }


    public ArrayList<String> getYerler() {
        return yerler;
    }

    public int[] getFiyatlandirma(int aracTipKodu) {
        int[][] fiyatlandirma = {
                {50,80,120,150,40},    // Sedan/Hatchback için fiyatlar.
                {70, 100, 150, 220,60},    // Suv için fiyatlar.
                {30, 45,80, 100,25},  // Motosiklet için fiyatlar.
                {100, 150, 200,300,70} // Minivan için fiyatlar.
        };
        return fiyatlandirma[aracTipKodu];
    }

    public int[] getGelenGunlukDizi() {
        return gelenGunluk;
    }

    public int[] getGelenToplamDizi() {
        return gelenToplam;
    }

    public int getGelenGunluk(){
        return gelenGunluk[0]+gelenGunluk[1]+gelenGunluk[2]+gelenGunluk[3];
    }

    public int getGelenToplam() {
        return gelenToplam[0]+gelenToplam[1]+gelenToplam[2]+gelenToplam[3];
    }

    public int getKapanisSaati() {
        return 23;
    }

    public int getGunlukCiro() {
        return gunlukCiro;
    }

    public int getToplamCiro() {
        return toplamCiro;
    }

    public int getToplamKar() {
        return toplamKar;
    }




}
