package com.example.arango.metodos.Metodos;


public class SistemasDeEcuaciones {
    private String mensaje;
    private String res;
    private double[][] tabla;
    //private double[] x;
    
    public String getRes(){
        return res;
    }
    
    public void imprimir(double[][] A){
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                System.out.print(A[i][j]+"\t");
            }
            System.out.println();
        }
    }
    
    public String getMensaje(){
        return mensaje;
    }
    
    public double[][] getTabla(){
        return tabla;
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
    
    public void agregarRes(double[][] A){
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                res+= A[i][j]+"\t";
            }
            res += "\n";
        }
    }
    
    public int[] hacerX(int n, int[] var){
        var = new int[n];
        for(int i=0;i<n;i++){
            var[i] = i;
        }
        return var;
    }
    
    public void prodMatriz(double[][] A, double[][] B){
        double[][] C = new double[A.length][B[0].length];
        double suma=0;
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B[0].length;j++){
                suma = 0;
                for(int k=0;k<A.length;k++){
                    suma = suma + A[i][k]*B[k][j];
                }
                C[i][j] = suma;
            }
        }
        agregarRes(C);
    }
    
    public void aumentada(double[][] A, double[] b){
        int n = A.length;
        int m = b.length;
        tabla = new double[n][n+1];
        if(n!=m){
            System.out.println("no es posible hacer la matriz aumentada A|b debido que"
                    + "las filas de A es diferente al numero de elementos de b");
        }else{
            for(int i=0;i<n;i++){
                for(int j=0;j<n+1;j++){
                    if(j==n){
                        tabla[i][j] = b[i];
                    }else{
                        tabla[i][j] = A[i][j];
                    }
                }
            }
        }
    }
    
    public double[][] cambioFila(double[][] A, int piv, int fCambio){
        int m = A[0].length;
        double aux;
        for(int i=0;i<m;i++){
            aux = A[piv][i];
            A[piv][i] = A[fCambio][i];
            A[fCambio][i] = aux;
        }
        return A;
    }
    
    public double[][] cambioCol(double[][] A, int piv, int cCambio){
        int n = A.length;
        double aux;
        for(int i=0;i<n;i++){
            aux = A[i][piv];
            A[i][piv] = A[i][cCambio];
            A[i][cCambio] = aux; 
        }            
        return A;
    }
    
    public int[] cambioVariables(int[] var, int piv, int cambio){
        int aux= var[piv];
        var[piv] = var[cambio];
        var[cambio] = aux;
        return var;
    }
    
    public void pivoteoParcial(int piv){
        int n = tabla.length;
        double mayor = Math.abs(tabla[piv][piv]);
        int fMayor = piv;
        for(int i=piv+1;i<n;i++){
            double aux = Math.abs(tabla[i][piv]);
            if(mayor < aux){
                fMayor = i;
                mayor = aux;
            }
        }
        if(mayor == 0){
            mensaje += "El sistema no tiene solución única.";
        }else{
            if(fMayor != piv){
                tabla = cambioFila(tabla,piv,fMayor); 
            }
        }
    } 
    
    public int[] pivoteoTotal(int piv, int[] variables){
        int n = tabla.length;
        double mayor = Math.abs(tabla[piv][piv]);
        int fMayor = piv;
        int cMayor = piv;
        for(int i=piv;i<n;i++){
            for(int j=piv;j<n;j++){
                double aux = Math.abs(tabla[i][j]);
                if(mayor < aux){
                    fMayor = i;
                    cMayor = j;
                    mayor = aux;
                }
            }
        }
        if(mayor == 0){
            mensaje = "El sistema no tiene solución única.";
            return variables;
        }else{
            if(fMayor != piv){
                tabla = cambioFila(tabla,piv,fMayor);                
            }
            if(cMayor != piv){
                tabla = cambioCol(tabla,piv,cMayor);
                variables = cambioVariables(variables,piv,cMayor);
            }
            return variables;
        }
    }
    
    public void pivoteoEscalonado(int piv){
        int n = tabla.length;
        double mayor = 0;
        double[] Mayores = new double[n-piv];
        double aux = 0;
        for(int i=piv;i<n;i++){
            mayor = Math.abs(tabla[i][piv]);
            for(int j=piv+1;j<n;j++){
                aux = Math.abs(tabla[i][j]);
                if(mayor < aux){
                    mayor = aux;
                }
            }
            Mayores[i-piv] = mayor;
        }
        mayor = 0;
        int fMayor = piv;
        int i=piv;
        while(i<n && Mayores[i-piv]!=0){
            aux = tabla[i][piv]/Mayores[i-piv];
            if(mayor < aux){
                mayor = aux;
                fMayor = i;
            }
            i++;
        }
        if(i!=n && Mayores[i-piv]==0){
            mensaje = "El sistema no tiene solución única.";
        }else{
            if(fMayor != piv){
                tabla = cambioFila(tabla,piv,fMayor);
            }
        }
    }
    
    public double[] eliminacionGauss(double[][] A, double[] b, int typePiv){
        int n = A.length;
        int l = A[0].length;
        tabla = new double[A.length][A[0].length+1];
        if(n!=l){
            mensaje = "A no es una matriz cuadrada.";
            return new double[0];
        }else{
            aumentada(A, b);
            int[] var = null;
            var = hacerX(tabla.length, var);          
            res = "";
            double m = 0;
            for(int k=0;k<n-1;k++){
                res += "Etapa "+(k+1)+"\n";
                switch(typePiv){
                    case 0:
                        pivoteoParcial(k);
                        break;
                    case 1:
                        var = pivoteoTotal(k,var);
                        break;
                    case 2:
                        pivoteoEscalonado(k);
                        break;
                    default:
                        break;
                }                
                for(int i=k+1;i<n;i++){
                    m = tabla[i][k] / tabla[k][k];
                    for(int j=k;j<n+1;j++){
                        tabla[i][j] = (double) tabla[i][j] - m*tabla[k][j];
                    }
                }                
                for(int i=0;i<n+1;i++){
                    if(i==n){
                        res+= "b";
                    }else{
                        res+= "x"+var[i]+"\t";
                    }
                }
                res+="\n";
                agregarRes(tabla);
                res+="\n";                
            }
            
            double suma;
            double[] x = new double[n];
            for(int i=n-1;i>=0;i--){
                suma=0;
                for(int j=i+1;j<n;j++){
                    suma = suma + tabla[i][j]*x[j];
                }
                 x[i] = (tabla[i][n] - suma)/tabla[i][i];
                res+= "x"+var[i]+" = "+x[i]+"\n";
            }
            return x;
        }        
    }
    
    public void LUeliminacionGauss(double[][] A, double[] b){
        int n = A.length;
        int l = A[0].length;
        tabla = new double[A.length][A[0].length+1];
        if(n!=l){
            mensaje = "A no es una matriz cuadrada.";
        }else{
            aumentada(A, b);         
            res = "";
            double m = 0;
            double[][] L = new double[A.length][A[0].length];
            double[][] U = new double[A.length][A[0].length];
            for(int k=0;k<n-1;k++){                
                res += "Etapa "+(k+1)+"\n";
                L[k][k]=1;
                for(int i=k+1;i<n;i++){
                    m = tabla[i][k] / tabla[k][k];
                    L[i][k] = m;
                    for(int j=k;j<n+1;j++){
                        tabla[i][j] = (double) tabla[i][j] - m*tabla[k][j];
                    }
                }
                res+="Matriz L\n";
                agregarRes(L);
                res+="\n";                
            } 
            res += "Etapa "+n+"\n";
            L[n-1][n-1]=1;
            res+= "Matriz L\n";
            agregarRes(L);
            res+="\n";
            
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    U[i][j] = tabla[i][j];
                }
            }
            
            res+= "Matriz U\n";
            agregarRes(U);
            res+="\n"; 
            
            double suma;
            double[] z = new double[n];
            for(int i=0;i<n;i++){
                suma=0;
                for(int j=0;j<i;j++){
                    suma = suma + L[i][j]*z[j];
                }
                 z[i] = (b[i] - suma);
                 res+= "z"+i+" = "+z[i]+"\n";
            }
            
            res+="\n";
            
            suma=0;
            double[] x = new double[n];
            for(int i=n-1;i>=0;i--){
                suma=0;
                for(int j=i+1;j<n;j++){
                    suma = suma + U[i][j]*x[j];
                }
                 x[i] = (z[i] - suma)/U[i][i];
                res+= "x"+i+" = "+x[i]+"\n";
            }            
        }        
    }
    
    public void cholesky(double[][] A, double[] b){
        int n = A.length;
        int l = A[0].length;
        if(n!=l){
            mensaje = "A no es una matriz cuadrada.";
        }else{
            res = "";
            double[][] L = new double[n][n];
            double[][] U = new double[n][n];
            double suma1,suma2,suma3;
            for(int k=0;k<n;k++){
                suma1=0;
                for(int p=0;p<k;p++){
                    suma1=suma1 + L[k][p]*U[p][k];
                }
                if(A[k][k]-suma1 < 0){
                    mensaje = "Error A["+k+","+k+"] es < 0";
                }
                L[k][k] = Math.sqrt(A[k][k]-suma1);
                System.out.println(A[k][k]+" "+suma1);
                U[k][k] = L[k][k];
                
                for(int i=k+1;i<n;i++){
                    suma2=0;
                    for(int r=0;r<k;r++){
                        suma2 = suma2 + L[i][r]*U[r][k];
                    }
                    L[i][k] = (A[i][k] - suma2)/L[k][k];                    
                }
                for(int j=k+1;j<n;j++){
                    suma3=0;
                    for(int s=0;s<k;s++){
                        suma3 = suma3 + L[k][s]*U[s][j];
                    }
                    U[k][j] = (A[k][j] - suma3)/L[k][k];
                }
                
                res+= "Etapa "+(k+1)+"\n";
                res+= "Matriz L"+"\n";
                agregarRes(L);
                res+= "Matriz U"+"\n";
                agregarRes(U);
                res+= "\n";  
            }
            
            double suma;
            double[] z = new double[n];
            for(int i=0;i<n;i++){
                suma=0;
                for(int j=0;j<i;j++){
                    suma = suma + L[i][j]*z[j];
                }
                 z[i] = (b[i] - suma)/L[i][i];
                 res+= "z"+i+" = "+z[i]+"\n";
            }

            res+="\n";

            suma=0;
            double[] x = new double[n];
            for(int i=n-1;i>=0;i--){
                suma=0;
                for(int j=i+1;j<n;j++){
                    suma = suma + U[i][j]*x[j];
                }
                 x[i] = (z[i] - suma)/U[i][i];
                res+= "x"+i+" = "+x[i]+"\n";
            }
        }
    }
    
    public void crout(double[][] A, double[] b){
        int n = A.length;
        int l = A[0].length;
        if(n!=l){
            mensaje = "A no es una matriz cuadrada.";
        }else{
            double[][] L = new double[n][n];
            double[][] U = new double[n][n];
            res = "";
            double suma1,suma2,suma3;
            for(int k=0;k<n;k++){
                suma1=0;
                for(int p=0;p<k;p++){
                    suma1=suma1 + L[k][p]*U[p][k];
                }
                L[k][k] = A[k][k]-suma1;
                if(L[k][k] == 0){
                    mensaje = "Error L["+k+","+k+"] es = 0";
                }
                U[k][k] = 1;
                
                for(int i=k+1;i<n;i++){
                    suma2=0;
                    for(int r=0;r<k;r++){
                        suma2 = suma2 + L[i][r]*U[r][k];
                    }
                    L[i][k] = A[i][k] - suma2;
                }
                for(int j=k+1;j<n;j++){
                    suma3=0;
                    for(int s=0;s<k;s++){
                        suma3 = suma3 + L[k][s]*U[s][j];
                    }
                    U[k][j] = (A[k][j] - suma3)/L[k][k];
                }
                
                res+= "Etapa "+(k+1)+"\n";
                res+= "Matriz L"+"\n";
                agregarRes(L);
                res+= "Matriz U"+"\n";
                agregarRes(U);
                res+= "\n";  
            }
            
            double suma;
            double[] z = new double[n];
            for(int i=0;i<n;i++){
                suma=0;
                for(int j=0;j<i;j++){
                    suma = suma + L[i][j]*z[j];
                }
                 z[i] = (b[i] - suma)/L[i][i];
                 res+= "z"+i+" = "+z[i]+"\n";
            }

            res+="\n";

            suma=0;
            double[] x = new double[n];
            for(int i=n-1;i>=0;i--){
                suma=0;
                for(int j=i+1;j<n;j++){
                    suma = suma + U[i][j]*x[j];
                }
                 x[i] = (z[i] - suma)/U[i][i];
                res+= "x"+i+" = "+x[i]+"\n";
            }
        }
    }
    
    public void doolittle(double[][] A, double[] b){
        int n = A.length;
        int l = A[0].length;
        if(n!=l){
            mensaje = "A no es una matriz cuadrada.";
        }else{
            double[][] L = new double[n][n];
            double[][] U = new double[n][n];
            double suma1,suma2,suma3;
            res = "";
            for(int k=0;k<n;k++){
                suma1=0;
                for(int p=0;p<k;p++){
                    suma1=suma1 + L[k][p]*U[p][k];
                }
                U[k][k] = A[k][k]-suma1;
                if(U[k][k] == 0){
                    mensaje = "Error U["+k+","+k+"] es = 0";
                }
                L[k][k] = 1;
                
                for(int i=k+1;i<n;i++){
                    suma2=0;
                    for(int r=0;r<k;r++){
                        suma2 = suma2 + L[i][r]*U[r][k];
                    }
                    L[i][k] = (A[i][k] - suma2)/U[k][k];
                }
                for(int j=k+1;j<n;j++){
                    suma3=0;
                    for(int s=0;s<k;s++){
                        suma3 = suma3 + L[k][s]*U[s][j];
                    }
                    U[k][j] = (A[k][j] - suma3);
                }
                res+= "Etapa "+(k+1)+"\n";
                res+= "Matriz L"+"\n";
                agregarRes(L);
                res+= "Matriz U"+"\n";
                agregarRes(U);
                res+= "\n";  
            }
            
            double suma;
            double[] z = new double[n];
            for(int i=0;i<n;i++){
                suma=0;
                for(int j=0;j<i;j++){
                    suma = suma + L[i][j]*z[j];
                }
                 z[i] = (b[i] - suma)/L[i][i];
                 res+= "z"+i+" = "+z[i]+"\n";
            }

            res+="\n";

            suma=0;
            double[] x = new double[n];
            for(int i=n-1;i>=0;i--){
                suma=0;
                for(int j=i+1;j<n;j++){
                    suma = suma + U[i][j]*x[j];
                }
                 x[i] = (z[i] - suma)/U[i][i];
                res+= "x"+i+" = "+x[i]+"\n";
            }        
        }
    }
    
    public void Jacobi(double[][] A, double [] b, double t, int iter, double[] x0, double lamda){
	int n = A.length;
        int l = A[0].length;
        mensaje = "";
        if(n!=l){
            mensaje = "A no es una matriz cuadrada.";
        }else{
            tabla = new double[1][x0.length+2];            
            double[] x = new double[n];
            double aux;
            int cont=0;
            double E= t+1;
            tabla[0][0] = cont;
            tabla[0][x0.length+1] = E;
            for(int i=1;i<=x0.length;i++){
                tabla[0][i] = x0[i-1];
            }
            double[] dato = new double[x0.length+2];
            while(E>t && cont<=iter){
               	E=0;
               	for(int i=0;i<n;i++){
                    double suma=0;
                  	for (int j=0;j<n; j++){
                            if (i!=j){
                           	suma=suma+A[i][j]*x0[j];
                            }
                   	}
                   	x[i] = lamda*((b[i] - suma)/A[i][i]) + (1-lamda)*x0[i];
                   	aux = x[i]-x0[i];
                   	E = E + Math.pow(aux,2);
               	}
               	E=Math.pow(E,0.5);
                dato[dato.length-1] = E;
               	for (int i=0;i<n;i++){
                    x0[i] = x[i];
                    dato[i+1]=x[i];
                }
                cont++;
                dato[0]=cont;
                agregarDatos(dato);
            }
            if(cont==iter){
               	mensaje = "no se llegó a la solución en "+iter+" iteraciones";
            }
    	}
    }
    
    public void Seidel(double[][] A, double [] b, double t, int iter, double[] x0, double lamda){
	int n = A.length;
        int l = A[0].length;
        mensaje = "";
        if(n!=l){
            mensaje = "A no es una matriz cuadrada.";
        }else{
            tabla = new double[1][x0.length+2];            
            double[] x = new double[n];
            double aux;
            int cont=0;
            double E= t+1;
            tabla[0][0] = cont;
            tabla[0][x0.length+1] = E;
            for(int i=1;i<=x0.length;i++){
                tabla[0][i] = x0[i-1];
            }
            double[] dato = new double[x0.length+2];
            while(E>t && cont<=iter){
               	E=0;
               	for(int i=0;i<n;i++){
                    double suma=0;
                  	for (int j=0;j<n; j++){
                            if (i!=j){
                           	suma=suma+A[i][j]*x0[j];
                            }
                   	}
                   	x[i] = lamda*((b[i] - suma)/A[i][i]) + (1-lamda)*x0[i];
                   	aux = x[i]-x0[i];
                   	E = E + Math.pow(aux,2);
                        x0[i]=x[i];
               	}
               	E=Math.pow(E,0.5);
                dato[dato.length-1] = E;
               	for (int i=0;i<n;i++){
                    dato[i+1]=x[i];
                }
                cont++;
                dato[0]=cont;
                agregarDatos(dato);
            }
            if(cont==iter){
               	mensaje = "no se llegó a la solución en "+iter+" iteraciones";
            }
    	}
    }
    
    
    
//    public double Neville(double V, double[]X){
//        double p=1;
//        int n=X.length;
//        for(int i=0;i<n;i++){
//            double aux=0;
//                for(int j=i+1;j<i;j++){
//                    aux=(((V-X[i])*p)-((V-X[j])*p-1))/(X[j]-X[i]);
//                }
//            p++;
//        }
//        
//        return p;
//    }
    
    
    
    
//    public void LUGaussParcial(double[][] A, double[] b){
//        int n = A.length;
//        int l = A[0].length;
//        matriz = new double[A.length][A[0].length+1];
//        if(n!=l){
//            mensaje = "A no es una matriz cuadrada.";
//        }else{
//            aumentada(A, b);         
//            res = "";
//            double m = 0;
//            double[][] L = new double[A.length][A[0].length];
//            double[][] U = new double[A.length][A[0].length];
//            for(int k=0;k<n-1;k++){
//                pivoteoParcial(k);
//                res += "Etapa "+(k+1)+"\n";
//                L[k][k]=1;
//                for(int i=k+1;i<n;i++){
//                    m = matriz[i][k] / matriz[k][k];
//                    L[i][k] = m;
//                    for(int j=k;j<n+1;j++){
//                        matriz[i][j] = (double) matriz[i][j] - m*matriz[k][j];
//                    }
//                }
//                
//                agregarRes(L);
//                res+="\n";                
//            } 
//            
//            L[n-1][n-1]=1;
//            res+= "Matriz L\n";
//            agregarRes(L);
//            res+="\n";
//            
//            for(int i=0;i<n;i++){
//                for(int j=0;j<n;j++){
//                    U[i][j] = matriz[i][j];
//                }
//            }
//            
//            res+= "Matriz U\n";
//            agregarRes(U);
//            res+="\n"; 
//            
//            double suma;
//            double[] z = new double[n];
//            for(int i=0;i<n;i++){
//                suma=0;
//                for(int j=0;j<i;j++){
//                    suma = suma + L[i][j]*z[j];
//                }
//                 z[i] = (b[i] - suma);
//                 res+= "z"+i+" = "+z[i]+"\n";
//            }
//            
//            res+="\n";
//            
//            suma=0;
//            x = new double[n];
//            for(int i=n-1;i>=0;i--){
//                suma=0;
//                for(int j=i+1;j<n;j++){
//                    suma = suma + U[i][j]*x[j];
//                }
//                 x[i] = (z[i] - suma)/U[i][i];
//                res+= "x"+i+" = "+x[i]+"\n";
//            }            
//        }        
//    }
    
}
