package functions;

public class Functions {
    public Functions(){}

    public double Cuadrado(double x){
        return Math.pow(x, 2);
    }

    public double Potencia(double x, double y){
        return Math.pow(x, y);
    }

    public double Raiz(double x, double y){
        return Math.pow(x, 1/y);
    }

    public double Multiplicacion(double x, double y){
        return x*y;
    }

    public double Divicion(double x, double y){
        if(y != 0){
            return x/y;
        }

        return 0;
    }

    public double Suma(double x, double y){
        return x+y;
    }

    public double Resta(double x, double y){
        return x-y;
    }
}
