
import Cola.java;
import PCB.java;
import CPU.java;
/*
 CODIGO DE AYUDA DE LOS PLANIFICADORES
 ROUND ROBIN Y COLAS DE PRIORIDAD

*/

class PlanficadorRR{

    // variables globales (solo de lectura)
    int N=100; // 100 procesos en teoria como maximo
    Cola Q=new Cola(); // cola Q
    PCB PRUN=new PCB();


    // EXAMEN PRIMER PARCIAL SA (1/2018) PREGUNTA 3
    // DEQUE (COLA MODIFICADA)
    boolean encolarNormal=true; int sucesion=0;
    void planificador_1parcial_SA_1_2018_q3(){
        if (Finalizo(PRUN)){
            FreeMem(PRUN);
        } else {
            PRUN.Reg=CPU.Reg;
            if (encolarNormal){
                Q.meterFinal(PRUN); 
            } else {
                Q.meterFrente(PRUN);
            }
        } 
        sucesion++;
        if (encolarNormal){
            PRUN=Q.sacarFrente();
        } else {
            PRUN=Q.sacarFinal();
        }
        if (sucesion%2==0){
            encolarNormal=!encolarNormal;
        }
        CPU.Reg=PRUN.Reg;
    }

    // PLANIFICADOR QUE ASIGNA QUANTUMS DE ACUERDO A LA SUCESION 1,0,1,0
    boolean darQ=true;
    void Planficador_sucesion_101010_(){
        if (Finalizo(PRUN)){
            FreeMem(PRUN);
        } else {
            PRUN.Reg=CPU.Reg;
            Q.meter(PRUN);
        }
        PRUN=Q.sacar();
        if (!darQ){
            Q.meter(PRUN);
            PRUN=Q.sacar();
        } 
        darQ=!darQ; // cambiar al sgte termino de la sucesion
        CPU.Reg=PRUN.Reg;

    }
    
    // EXAMEN DE AYUDANTIA (2018) PREGUNTA 3
    // estado de ready si ya estuvo 3 veces en wait
    void planificador_testAux_2018_q3(){
        PRUN.Reg=CPU.Reg;
        Q.meter(PRUN);
        PRUN=getPCB();
        CPU.Reg=PRUN.Reg;
    }

    PCB getPCB(){
        PCB p=Q.sacar();
        p.waitCount++;
        while (p.waitCount<=3){
            Q.meter(p);
            p=Q.sacar();
            p.waitCount++;
        }
        return p;
    }
    
}