package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread{

	private Semaphore semaforo;
	private int tempoTotal;
	private String prato;
	
	public ThreadCozinha(Semaphore semaforo){
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		if(getId()%2==1){
			tempoTotal = (int)(Math.random() * 3) + 5;
			prato = "Sopa de Cebola";
			Cozinha();
			try {
				semaforo.acquire();
				Entrega();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				semaforo.release();
			}
		}else if(getId()%2==0){
			tempoTotal = (int)(Math.random() * 6) + 6;
			prato = "Lasanha Bolonhesa";
			Cozinha();
			try{
				semaforo.acquire();
				Entrega();
			} catch (InterruptedException e){
				e.printStackTrace();
			}finally{
				semaforo.release();
			}
		}
	}
	
	private void Cozinha(){
		double porcentagem = 0;
		System.out.println("A "+prato+" "+getId()+" começou a ser cozinhada!");
		for(int i=0;i<tempoTotal;i++){
			porcentagem = porcentagem + 100/tempoTotal;
			System.out.println("A "+prato+" "+getId()+" está cozinhando: "+porcentagem+"%");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("A "+prato+" "+getId()+" está pronta!");
	}
	
	private void Entrega(){
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A "+prato+" "+getId()+" foi entregue!");
	}
}
