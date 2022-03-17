package elsohazifeladat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//1.feladat
		
		Integer[] kmTomb = {1200,1400,2100,3000,3000,3500,4500,5620,6750,8420,92010,10520};
		int kmAllas = 100;
		System.out.println("A tomb elemei: "+Arrays.toString(kmTomb));
		
		//5.feladat
		
		String[] honapok = {"januar","februar","marcius","aprilis","majus","junius","julius","augusztus","szeptember","oktober","november","december"};
		System.out.println("-------------------------");
		
		//2.feladat
		
		System.out.println("2.feladat: ");
		int[] haviFutasTeljesitmeny = new int[12];
		int index = 0;
		
		for(int i = 0; i < kmTomb.length; i++) {
			haviFutasTeljesitmeny[i] += kmTomb[i]/kmAllas;
			index = i;
			System.out.println(honapok[i]+" "+haviFutasTeljesitmeny[i]);
		}
		
		System.out.println("-------------------------");

		//3.feladat
		
		int bekertSzam;
		do {
			System.out.print("3.feladat: adjon meg egy 0-tol nagyobb de 12-tol kisebb vagy egyenlo szamot: ");
			Scanner sc = new Scanner(System.in);
			bekertSzam = Integer.parseInt(sc.next());
		}while(!(bekertSzam>0 && bekertSzam<=12));
		System.out.println("-------------------------");
		
		//4.feladat
		
		System.out.println("4.feladat: ");
		int[] kevesebb = new int[12];
		int[] tobb = new int[12];
		int kevesebbIndex = 0;
		int tobbIndex = 0;
		for(int i=0;i<haviFutasTeljesitmeny.length;i++) {
			if(i < bekertSzam) {
				kevesebb[kevesebbIndex] = haviFutasTeljesitmeny[i];
				kevesebbIndex++;
			}else if(i > bekertSzam){
				tobb[tobbIndex] = haviFutasTeljesitmeny[i];
				tobbIndex++;
			}
		}
		System.out.println("A kevesebb tomb elemei:");
		for(int i=0;i<kevesebb.length;i++) {
			System.out.println(kevesebb[i]);
		}
		System.out.println("A tobb tomb elemei:");
		for(int i=0;i<tobb.length;i++) {
			System.out.println(tobb[i]);
		}
		System.out.println("-------------------------");
		
		//6.feladat
		System.out.println("6.feladat: koltseg.txt file letrehozva.");
		
		List<String> sorok = new ArrayList<String>();
		String fejlec =  "Honap | Indulo km | Zaro km | Futasteljesitmeny | Uzemanyag ktg\n";
		sorok.add(fejlec);
		double atlagfogyasztas = 6.3;
		Random random = new Random();
		
		for(int i=0; i<kmTomb.length; i++) {
			kmTomb[i] = random.nextInt(410 - 360) + 360;
			double fogyasztas = kmTomb[i]*atlagfogyasztas;
			String sor = "";
			sor += honapok[i]+"		"+kmTomb[i]+"		"+kmTomb[i]+"		"+haviFutasTeljesitmeny[i]+"		"+String.format("%.2f", fogyasztas)+"\n";
			sorok.add(sor);
		}
		for(int i=0;i<kmTomb.length;i++) {
			String sor = "";
			double fogyasztas = kmTomb[i]*atlagfogyasztas;
			sor += fogyasztas/kmTomb.length;
			sorok.add("Az eves uzemanyagkoltseg: "+sor);
		}
		try {
			RandomAccessFile io = new RandomAccessFile("koltsegek.txt","rw");
			for(int i=0;i<sorok.size();i++) {
				try {
					io.writeBytes(sorok.get(i));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			try {
				io.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("-------------------------");
	}
}
