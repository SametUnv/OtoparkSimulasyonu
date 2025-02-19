import java.time.LocalTime;
import java.util.Random;

class Rastgele {
    Random random = new Random();


    public String setRplaka() {
        int rastgeleSayi1 = random.nextInt(1,82);
        String plakaHarfleri = "ABCDEFGHIKLMNOPRSTUVYZ";
        StringBuilder rastgeleHarf0 = new StringBuilder();
        int kacHaneli = random.nextInt(1, 4);

        for(int i=0;i<kacHaneli;i++){
            rastgeleHarf0.append(plakaHarfleri.charAt(random.nextInt(plakaHarfleri.length())));
        }
        int rastgeleSayi2 = random.nextInt(0, 1000);
        String rastgeleSayik1 = (rastgeleSayi1 < 10) ? "0" + rastgeleSayi1 : String.valueOf(rastgeleSayi1);
        String rastgeleSayik2;
        if(rastgeleSayi2<10) {
            rastgeleSayik2 = "00"+rastgeleSayi2;}
        else{
            rastgeleSayik2 = String.valueOf(rastgeleSayi2);
        }
        return rastgeleSayik1+" " + rastgeleHarf0 +" "+rastgeleSayik2;
    }

    public LocalTime setRkalmaSuresi(LocalTime girisSaati) {
        int saat=random.nextInt(0,24-girisSaati.getHour());
        int dakika =random.nextInt(0,60);
        if(dakika+girisSaati.getMinute()>=60&&girisSaati.getHour()!=23){
            saat=random.nextInt(0,23-girisSaati.getHour());
        }else{
            dakika =random.nextInt(0,60-girisSaati.getMinute());}
        return LocalTime.of(saat,dakika);
    }


    public String setRmarka(String aracTipi) {
        String[] otomobilMarkalari = {"Audi", "Bentley", "BMW", "Bugatti",
                "Chevrolet", "Citroën", "Dacia", "DS", "Fiat", "Ford", "Honda", "Jeep", "Kia",
                "Lada", "Lamborghini", "Maserati", "Maybach", "Mazda", "Mercedes-Benz", "MG", "Mini",
                "Mitsubishi", "Morgan", "Nissan", "Opel", "Peugeot", "Porsche", "Renault", "Rover",
                "Seat", "Skoda", "SsangYong", "Tesla", "Toyota", "Volkswagen", "Volvo"};
        String[] motosikletMarkalari = {"KTM", "Peugeot", "BMW", "Aprilia", "Benelli", "Ducati", "Piaggio",
                "MV Agusta", "Triumph", "Bajaj", "Hero", "TVS", "Royal Enfield",
                "Honda", "Kawasaki", "Suzuki", "Yamaha", "Daelim", "Hyosung",
                "Kymco", "Sym"};
        if (aracTipi.equals("Motosiklet")) {
            int motoSecim = random.nextInt(motosikletMarkalari.length);
            return motosikletMarkalari[motoSecim];
        }
        int otoSecim = random.nextInt(otomobilMarkalari.length);
        return otomobilMarkalari[otoSecim];
    }

    public String setRtip() {
        String[] tipler = {"Sedan/Hatchback","Suv", "Motosiklet","Minivan"};
        int secim = random.nextInt(tipler.length);
        return tipler[secim];
    }

    public boolean setRyikama(){
        int secim = random.nextInt(0,5);
        return secim == 1;
    }

}