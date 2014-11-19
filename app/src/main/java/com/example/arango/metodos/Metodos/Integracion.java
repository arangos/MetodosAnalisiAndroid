package com.example.arango.metodos.Metodos;

import com.example.arango.metodos.Parser.Funcion;

/**
 * Created by Felipe on 18/11/2014.
 */
public class Integracion {
    private Funcion fx;
    private String res;
    private String mensaje;

    public Integracion(String funcion){
        this.fx = new Funcion(funcion);
        mensaje= "";
    }

    private double f(double x){
        x = fx.evaluar(x);
        return x;
    }

    public String getRes(){
        return res;
    }

    public String getMensaje(){
        return mensaje;
    }

    public void trapecioSimple(double x0, double xn){
        double h= (xn - x0);
        double integ = f(x0);
        integ += f(xn);
        integ = (h/2)*integ;
        res="";
        res+="El área bajo la curva "+fx.getFuncion()+" entre el intervalo ("+x0+","+xn+") es: "+integ+" + E";
    }

    public void trapecioCompuesto(double x0, double xn, int n){
        double h= (xn - x0)/n;
        double integ = f(x0);
        double suma = 0;
        res = "";
        double aux;
        for(int i=1;i<n;i++){
            aux = f(x0+ i*h);
            suma = suma + aux;
        }
        integ += 2*suma;
        integ += f(xn);
        integ = (h/2)*integ;
        res+="El área bajo la curva "+fx.getFuncion()+" entre el intervalo ("+x0+","+xn+") es: "+integ+" + E";
    }

    public void simpson13(double x0, double xn){
        double h= (xn - x0)/2;
        double integ = f(x0);
        res = "";
        integ += 4*f(x0+h);
        integ += f(xn);
        integ = (h/3)*integ;
        res+="El área bajo la curva "+fx.getFuncion()+" entre el intervalo ("+x0+","+xn+") es: "+integ+" + E";
    }

    public void simpson13Compuesto(double x0, double xn, int n){
        if(n%2!=0){
            mensaje = "El numero de intervalos debe ser par.";
        }else{
            double h= (xn - x0)/n;
            double integ = f(x0);
            double suma1 = 0;
            double suma2 = 0;
            res = "";
            for(int i=1;i<n;i++){
                if(i%2==1){
                    suma1 += f(x0+ i*h);
                }else{
                    suma2 += f(x0+ i*h);
                }

            }
            integ += 4*suma1;
            integ += 2*suma2;
            integ += f(xn);
            integ = (h/3)*integ;
            res+="El área bajo la curva "+fx.getFuncion()+" entre el intervalo ("+x0+","+xn+") es: "+integ+" + E";
        }
    }

    public void simpson38(double x0, double xn){
        double h= (xn - x0)/3;
        double integ = f(x0);
        integ += 3*f(x0+h);
        integ += 3*f(x0 + 2*h);
        integ += f(xn);
        integ = (3*h/8)*integ;
        res+="El área bajo la curva "+fx.getFuncion()+" entre el intervalo ("+x0+","+xn+") es: "+integ+" + E";
    }

    public void simpson38Compuesto(double x0, double xn, int n){
        if(n%3!=0){
            mensaje = "El numero de intervalos debe ser un multiplo de tres.";
        }else{
            double h= (xn - x0)/n;
            double integ = f(x0);
            double suma1 = 0;
            double suma2 = 0;
            double suma3 =0;
            res = "";
            for(int i=1;i<n;i++){
                if(i%3==1){
                    suma1 += f(x0+ i*h);
                }else{
                    if(i%3==2){
                        suma2 += f(x0+ i*h);
                    }else{
                        suma3 += f(x0+i*h);
                    }
                }
            }
            integ += 3*suma1;
            integ += 3*suma2;
            integ += 2*suma3;
            integ += f(xn);
            integ = (3*h/8)*integ;
            res+="El área bajo la curva "+fx.getFuncion()+" entre el intervalo ("+x0+","+xn+") es: "+integ+" + E";
        }
    }

}

