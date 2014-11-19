package com.example.arango.metodos.Metodos;

import com.example.arango.metodos.Parser.Funcion;

public class EcNoLineales {
    private double[][] tabla;
    private String mensaje;
    private Funcion fx;
    private Funcion dfx;
    private Funcion ddfx;
    private Funcion gx;

    public EcNoLineales(String funcion){
        this.fx = new Funcion(funcion);
    }

    public void setdfx(String dfx){
        this.dfx = new Funcion(dfx);
    }

    public void setddfx(String ddfx){
        this.ddfx = new Funcion(ddfx);
    }

    public void setgx(String gx){
        this.gx = new Funcion(gx);
    }

    public double[][] getTabla(){
        return tabla;
    }

    public String getMensaje(){
        return mensaje;
    }

    private double f(double x){
        x = fx.evaluar(x);
        return x;
    }

    private double df(double x){
        x = dfx.evaluar(x);
        return x;
    }

    private double ddf(double x){
        x = ddfx.evaluar(x);
        return x;
    }

    private double g(double x){
        x = gx.evaluar(x);
        return x;
    }

    private void agregarDatos(double[] dato){
        double[][] aux = new double[tabla.length][tabla[0].length];
        for(int i=0;i<tabla.length;i++){
            for(int j=0;j<tabla[0].length;j++){
                aux[i][j] = tabla[i][j];
            }
        }

        tabla = new double[tabla.length+1][tabla[0].length];
        for(int i=0;i<aux.length;i++){
            for(int j=0;j<aux[0].length;j++){
                tabla[i][j] = aux[i][j];
            }
        }
        for(int i=0;i<dato.length;i++){
            tabla[aux.length][i] = dato[i];
        }
    }

    public void busquedas(double x0,double delta,int iter){
        double y0 = f(x0);
        tabla = new double[1][2];
        tabla[0][0] = x0;
        tabla[0][1] = y0;
        if(y0 == 0){
            mensaje = "Hay una Raiz en x = "+x0;
        }else{
            double x1 = x0 + delta;
            double y1 = f(x1);
            int cont = 1;
            double[] aux = {x1,y1};
            agregarDatos(aux);
            while (y1*y0 > 0 && cont < iter){
                x0 = x1;
                y0 = y1;
                x1 = x0 + delta;
                y1 = f(x1);
                cont++;
                aux[0] = x1;
                aux[1] = y1;
                agregarDatos(aux);
            }
            if (y1 == 0) {
                mensaje = "Hay una raiz en x = "+x1;
            } else {
                if (y1*y0 < 0) {
                    mensaje = "Hay una raiz en el intervalo ["+x0+","+x1+"]";
                } else {
                    mensaje = "No se encontro intervalo en "+iter+" iteraciones";
                }
            }
        }
    }

    public void biseccion(double a, double b, double t, int i){
        double Ya = f(a);
        double Yb = f(b);
        tabla = new double[1][6];
        if(Ya*Yb > 0){
            mensaje = "El intervalo ["+a+","+b+"] no es valido";
        }else{
            if(Ya == 0){
                mensaje = "Hay una raiz en x = "+a;
            }else{
                if(Yb == 0){
                    mensaje = "Hay una raiz en x = "+b;
                }else{
                    double aux;
                    double Xm = (a+b)/2;
                    double E = t+1;
                    int cont=1;
                    double Ym = f(Xm);
                    tabla[0][0] = cont;
                    tabla[0][1] = a;
                    tabla[0][2] = b;
                    tabla[0][3] = Xm;
                    tabla[0][4] = Ym;
                    double[] Taux = new double[6];
                    while(Ym!=0 && E>t && cont<i){
                        if(Ya*Ym<0){
                            b=Xm;
                        }else{
                            a=Xm;
                            Ya=f(a);
                        }
                        aux = Xm;
                        Xm = (a+b)/2;
                        Ym = f(Xm);
                        E = Math.abs(Xm - aux);
                        cont++;
                        Taux[0] = cont;
                        Taux[1] = a;
                        Taux[2] = b;
                        Taux[3] = Xm;
                        Taux[4] = Ym;
                        Taux[5] = E;
                        agregarDatos(Taux);
                    }
                    if(Ym == 0){
                        mensaje = "Hay una raiz en x = "+Xm;
                    }else{
                        if(E<=t){
                            mensaje = "Hay una raiz en x = "+Xm+" con un Error de "+E;
                        }else{
                            mensaje = "Fracaso: no se halló raiz en "+i+" iteraciones";
                        }
                    }
                }
            }
        }
    }

    public void reglaFalsa(double a, double b, double t, int i){
        double Ya = f(a);
        double Yb = f(b);
        tabla = new double[1][6];
        if(Ya*Yb > 0){
            mensaje = "El intervalo ["+a+","+b+"] no es valido";
        }else{
            if(Ya == 0){
                mensaje = "Hay una raiz en x = "+a;
            }else{
                if(Yb == 0){
                    mensaje = "Hay una raiz en x = "+b;
                }else{
                    double aux;
                    double Xm = a - Ya*((b-a)/(Yb-Ya));
                    double E = t+1;
                    int cont=1;
                    double Ym = f(Xm);
                    tabla[0][0] = cont;
                    tabla[0][1] = a;
                    tabla[0][2] = b;
                    tabla[0][3] = Xm;
                    tabla[0][4] = Ym;
                    double[] Taux = new double[6];
                    while(Ym!=0 && E>t && cont<i){
                        if(Ya*Ym<0){
                            b=Xm;
                            Yb=f(b);
                        }else{
                            a=Xm;
                            Ya=f(a);
                        }
                        aux = Xm;
                        Xm = a - Ya*((b-a)/(Yb-Ya));
                        Ym = f(Xm);
                        E = Math.abs(Xm - aux);
                        cont++;
                        Taux[0] = cont;
                        Taux[1] = a;
                        Taux[2] = b;
                        Taux[3] = Xm;
                        Taux[4] = Ym;
                        Taux[5] = E;
                        agregarDatos(Taux);
                    }
                    if(Ym == 0){
                        mensaje = "Hay una raiz en x = "+Xm;
                    }else{
                        if(E<=t){
                            mensaje = "Hay una raiz en x = "+Xm+" con un Error de "+E;
                        }else{
                            mensaje = "Fracaso: no se halló raiz en "+i+" iteraciones";
                        }
                    }
                }
            }
        }
    }

    public void puntoFijo(double Xm, double t, int i){
        double aux;
        double E = t+1;
        int cont=1;
        double Ym = f(Xm);
        tabla = new double[1][4];
        tabla[0][0] = cont;
        tabla[0][1] = Xm;
        tabla[0][2] = Ym;
        double[] Taux = new double[4];
        while(Ym!=0 && E>t && cont<i){
            aux = Xm;
            Xm = g(Xm);
            Ym = f(Xm);
            E = Math.abs(Xm - aux);
            cont++;
            Taux[0] = cont;
            Taux[1] = Xm;
            Taux[2] = Ym;
            Taux[3] = E;
            agregarDatos(Taux);
        }
        if(Ym == 0){
            mensaje = "Hay una raiz en x = "+Xm;
        }else{
            if(E<=t){
                mensaje = "Hay una raiz en x = "+Xm+" con un Error de "+E;
            }else{
                mensaje = "Fracaso: no se halló raiz en "+i+" iteraciones";
            }
        }
    }

    public void newton(double Xm, double t, int i){
        double aux;
        double E = t+1;
        int cont=1;
        double Ym = f(Xm);
        double Y1m = df(Xm);
        tabla = new double[1][4];
        tabla[0][0] = cont;
        tabla[0][1] = Xm;
        tabla[0][2] = Ym;
        double[] Taux = new double[4];
        while(Ym!=0 && E>t && cont<i && Y1m!=0){
            aux = Xm;
            Xm = Xm - (Ym/Y1m);
            Ym = f(Xm);
            Y1m = df(Xm);
            E = Math.abs(Xm - aux);
            cont++;
            Taux[0] = cont;
            Taux[1] = Xm;
            Taux[2] = Ym;
            Taux[3] = E;
            agregarDatos(Taux);
        }
        if(Ym == 0){
            mensaje = "Hay una raiz en x = "+Xm;
        }else{
            if(E<=t){
                mensaje = "Hay una raiz en x = "+Xm+" con un Error de "+E;
            }else{
                if(Y1m==0){
                    mensaje = "El método de Newton no es apropiado para este caso ya que f'("+Xm+") = 0";
                }else{
                    mensaje = "Fracaso: no se halló raiz en "+i+" iteraciones";
                }
            }
        }
    }

    public void secante(double a, double b, double t, int i){
        double Ya=f(a);
        double Yb=f(b);
        if(Ya == 0){
            System.out.println("Hay una raiz en x = "+a);
        }else{
            if(Yb == 0){
                System.out.println("Hay una raiz en x = "+b);
            }else{
                double Xm = b - Yb*((b-a)/(Yb-Ya));
                double Ym = f(Xm);
                double aux = b;
                double Yaux = f(aux);
                double aux2=Xm;
                double Yaux2=Ym;
                double E= t+1;
                int cont=1;
                tabla = new double[1][4];
                tabla[0][0] = cont;
                tabla[0][1] = Xm;
                tabla[0][2] = Ym;
                double[] Taux = new double[4];
                while(Ym!=0 && E>t && cont<i){
                    Xm = aux2 - Yaux2*((aux2-aux)/(Yaux2-Yaux));
                    Ym = f(Xm);
                    aux = aux2;
                    Yaux = Yaux2;
                    aux2 = Xm;
                    Yaux2 = Ym;
                    E=Math.abs(Xm-aux);
                    cont++;
                    Taux[0] = cont;
                    Taux[1] = Xm;
                    Taux[2] = Ym;
                    Taux[3] = E;
                    agregarDatos(Taux);
                }
                if(Ym == 0){
                    mensaje = "Hay una raiz en x = "+Xm;
                }else{
                    if(E<=t){
                        mensaje = "Hay una raiz en x = "+Xm+" con un Error de "+E;
                    }else{
                        mensaje = "Fracaso: no se halló raiz en "+i+" iteraciones";
                    }
                }
            }
        }
    }

    public void raicesMulti (double Xm, double t, int i) {
        double E = t+1;
        int cont = 0;
        double y = f(Xm);
        double z = df(Xm);
        double w = ddf(Xm);
        double den = Math.pow(z, 2)- y*w;
        double aux = Xm;
        tabla = new double[1][6];
        tabla[0][0] = cont;
        tabla[0][1] = Xm;
        tabla[0][2] = y;
        tabla[0][3] = z;
        tabla[0][4] = w;
        double[] Taux = new double[6];
        while (y!=0 && E>t && cont<i && z!=0 && den!=0) {
            Xm = Xm - (y*z)/den;
            y = f(Xm);
            z = df(Xm);
            w = ddf(Xm);
            E = Math.abs(Xm - aux);
            aux = Xm;
            cont++;
            Taux[0] = cont;
            Taux[1] = Xm;
            Taux[2] = y;
            Taux[3] = z;
            Taux[4] = w;
            Taux[5] = E;
            agregarDatos(Taux);
        }
        if (y == 0) {
            mensaje = "Hay una raiz en x = "+Xm;
        } else {
            if (E<=t) {
                mensaje = "Hay una raiz en x = "+Xm+" con una tolerancia de "+E;
            } else {
                if (den==0) {
                    mensaje = "posiblemente hay una raíz en "+Xm+" de multiplicidad > dos";
                } else {
                    if(z==0){
                        mensaje = "El método de Raices Multiples no es apropiado para este caso"; // ya que f'("+Xm+") = 0
                    }else{
                        mensaje = "Fracaso: no se halló raiz en "+i+" iteraciones";
                    }
                }
            }
        }
    }
}

