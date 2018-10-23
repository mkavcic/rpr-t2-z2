package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetnaTacka, krajnjaTacka;
    private boolean pripadaPocetna, pripadaKrajnja;

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pripadaPocetna, boolean pripadaKrajnja) {
        if (pocetnaTacka > krajnjaTacka) throw new IllegalArgumentException("Pocetna tacka je veca od krajnje!");
        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pripadaPocetna = pripadaPocetna;
        this.pripadaKrajnja = pripadaKrajnja;
    }

    public Interval() {
        pocetnaTacka = 0;
        krajnjaTacka = 0;
        pripadaKrajnja = false;
        pripadaPocetna = false;
    }

    public boolean isNull(){
        return pocetnaTacka == 0 && krajnjaTacka == 0 && !pripadaPocetna && pripadaKrajnja == false;
    }

    public boolean isIn(double Tacka){
        if(pripadaPocetna){
            if(pripadaKrajnja){
                return Tacka >= pocetnaTacka && Tacka <= krajnjaTacka;
            }
            else return Tacka >= pocetnaTacka && Tacka < krajnjaTacka;
        }
        else {
            if(pripadaKrajnja){
                if(Tacka>pocetnaTacka&&Tacka<=krajnjaTacka) return true;
            }
        }
        if(!pripadaKrajnja && !pripadaKrajnja){
            return Tacka > pocetnaTacka && Tacka < krajnjaTacka;
        }
        return false;
    }

    public static Interval intersect(Interval i1, Interval i2) {
        Interval i = new Interval();
        double a = i1.pocetnaTacka, b = i1.krajnjaTacka, c = i2.pocetnaTacka, d = i2.krajnjaTacka;
        if (a < c && a < d && b < c && b < d )
            return i;
        else if (a > c && a > d && b > c && b > d)
            return i;
        else if (a < c && c < b && b < d  && a < d) {
            if (i2.pripadaPocetna && i1.pripadaKrajnja)
                i = new Interval(c, b, true, true);
            else if (i2.pripadaPocetna && !i1.pripadaKrajnja)
                i = new Interval(c, b, true, false);
            else if (!i2.pripadaPocetna && i1.pripadaKrajnja)
                i = new Interval(c, b, false, true);
            else i = new Interval(c, b, false, false);
        }
        else if (d > a && d > b  && c < a && c < b ) {
            if (i1.pripadaPocetna && i1.pripadaKrajnja)
                i = new Interval(a, b, true, true);
            else if (i1.pripadaPocetna)
                i = new Interval(a, b, true, false);
            else if (i1.pripadaKrajnja)
                i = new Interval(a, b, false, true);
            else i = new Interval(a, b, false, false);
        }
        else if (a > c && a < d && b > c && b > d ) {
            if (i1.pripadaPocetna && i2.pripadaKrajnja)
                i = new Interval(a, d, true, true);
            else if (i1.pripadaPocetna)
                i = new Interval(a, d, true, false);
            else if (!i1.pripadaPocetna && i2.pripadaKrajnja)
                i = new Interval(a, d, false, true);
            else i = new Interval(a, d, false, false);
        }
        else if (b > c && b > d &&  d > a && c > a) {
            if(i2.pripadaPocetna && i2.pripadaKrajnja)
                i = new Interval(c, d, true, true);
            else if(i2.pripadaPocetna && !i2.pripadaKrajnja)
                i = new Interval(c, d, true, false);
            else if(!i2.pripadaPocetna && i2.pripadaKrajnja)
                i = new Interval(c, d, false, true);
            else
                i = new Interval(c, d, false, false);
        }
        return i;
    }

    public Interval intersect(Interval i){
        Interval inter = new Interval();
        double a = i.pocetnaTacka, b = i.krajnjaTacka, c = this.pocetnaTacka, d = this.krajnjaTacka;
        if (a < c && a < d && b < c && b < d )
            return inter;
        else if (a > c && a > d && b > c && b > d)
            return inter;
        else if (a < c && c < b && b < d  && a < d) {
            if (this.pripadaPocetna && i.pripadaKrajnja)
                inter = new Interval(c, b, true, true);
            else if (this.pripadaPocetna && !i.pripadaKrajnja)
                inter = new Interval(c, b, true, false);
            else if (!this.pripadaPocetna && i.pripadaKrajnja)
                inter = new Interval(c, b, false, true);
            else inter = new Interval(c, b, false, false);
        }

        else if (d > a && d > b  && c < a && c < b ) {
            if (i.pripadaPocetna && i.pripadaKrajnja)
                inter = new Interval(a, b, true, true);
            else if (i.pripadaPocetna && !i.pripadaKrajnja)
                inter = new Interval(a, b, true, false);
            else if (!i.pripadaPocetna && i.pripadaKrajnja)
                inter = new Interval(a, b, false, true);
            else inter = new Interval(a, b, false, false);
        }

        else if (a > c && a < d && b > c && b > d ) {
            if (i.pripadaPocetna && this.pripadaKrajnja)
                inter = new Interval(a, d, true, true);
            else if (i.pripadaPocetna && !this.pripadaKrajnja)
                inter = new Interval(a, d, true, false);
            else if (!i.pripadaPocetna && this.pripadaKrajnja)
                inter = new Interval(a, d, false, true);
            else inter = new Interval(a, d, false, false);
        }

        else if (b > c && b > d &&  d > a && c > a) {
            if(this.pripadaPocetna && this.pripadaKrajnja)
                inter = new Interval(c, d, true, true);
            else if(this.pripadaPocetna && !this.pripadaKrajnja)
                inter = new Interval(c, d, true, false);
            else if(this.pripadaKrajnja)
                inter = new Interval(c, d, false, true);
            else
                inter = new Interval(c, d, false, false);
        }
        return inter;
    }

    @Override public boolean equals(Object o) {
        if(o instanceof Interval){
            Interval i= (Interval) o;
            if (i.pocetnaTacka == this.pocetnaTacka) if (i.krajnjaTacka == this.krajnjaTacka)
                if (i.pripadaPocetna == this.pripadaPocetna) return i.pripadaKrajnja == this.pripadaKrajnja;
            return false;
        }
        return false;
    }

    @Override
    public String toString(){
        String s="";
        if(pripadaPocetna){
            s+="[";
        }
        else s+="(";
        s+=pocetnaTacka+","+krajnjaTacka;
        if(pripadaKrajnja) s+="]";
        else s+=")";
        if(pocetnaTacka==0&&krajnjaTacka==0) s="()";
        return s;
    }
}