package com.example.arango.metodos.Metodos;

/**
 * Created by Felipe on 18/11/2014.
 */
public class Interpolacion {
    public String res;
    public double[][] tabla;

    public void Newton (double[] F, double[] X, double val){
        res = "";
        int n = X.length;
        tabla = new double[n][n];
        for (int j=0;j<n;j++){
            tabla[j][0]=F[j];
        }
        int q;
        for (int k=0; k<n-1;k++){
            q=0;
            for(int i=k+1;i<n;i++){
                tabla[i][k+1]=(tabla[i][k]-tabla[i-1][k])/(X[i]-X[q]);
                q++;
            }
        }

        agregarRes(tabla);
        double p=0;
        double aux;
        res+="\nPolinomio Interpolante:\n";
        res+="p(x) = ";
        for(int s=0;s<n;s++){
            aux=1;

            if(tabla[s][s]>=0 && s!=0){
                res+= "+";
            }
            res+= tabla[s][s];

            for(int c=0;c<s;c++){
                res+= "(x-"+X[c]+")";
                aux=aux*(val-X[c]);
            }
            res+=" ";
            p = p + tabla[s][s]*aux;
        }
        res+="\n\nValor Buscado\n";
        res+= "p("+val+") = "+p;
    }


    public void Lagrange (double[] F, double[] X, double val){
        double p=0;
        int n = X.length;
        double den;
        double num;
        res="";
        String aux = "";
        res = "\nPolinomio Interpolante:\n";
        res += "p(x) = ";
        for(int i=0;i<n;i++){
            den=1;
            num=1;
            aux = "";
            for(int j=0;j<n;j++){
                if(j!=i){
                    den = (X[i]-X[j])*den;
                    num=(val-X[j])*num;
                    aux+= "(x ";
                    if(X[j]<0){
                        aux+= "+ "+Math.abs(X[j])+")";
                    }else{
                        aux+= "- "+X[j]+")";
                    }
                }
            }
            if(F[i]/den >= 0 && i!=0){
                res+=" +";
            }
            res+=" "+(F[i]/den)+aux;
            p=p+((num/den)*F[i]);
        }
        res += "\np("+val+") = ";
        res+= p;
    }

    public void agregarRes(double[][] A){
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                res+= A[i][j]+"\t";
            }
            res += "\n";
        }
    }
    public String getRes(){
        return res;
    }
}
