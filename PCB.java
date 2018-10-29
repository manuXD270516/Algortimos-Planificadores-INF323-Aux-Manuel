private class PCB{
    int PID;
    byte[] Reg;
    // campo para los planificadores con colas de prioridad
    int Prioirdad;

    // algun campo adicional
    // campo para la pregunta 3 exam ayudantia 2018
    int readyCount=1; // cantidad de veces en estado ready

    // campo para la pregunta 2 examen prim parcial sc 2/2016
    int c;

    public PCB(){
        this.PID=-1;
        this.Reg=new byte[1000]; 
    }

    
}