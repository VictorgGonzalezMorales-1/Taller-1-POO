package Taller_1;

/*Integrantes del Taller:
 * 
 * Victor González Morales,  Rut: 220615529
 * Joaquín Torres Flores,  Rut: 215473708
 * 
 * */



//Importar librerias para axtivar las funciones
import java.util.Scanner;
import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		/*Generar Scanner Global, este lo emplearé durante todo el código
		 *para ahorrar espacio usandolo como una variable en los métodos*/
		Scanner scanner = new Scanner(System.in);
		
		/*Generar Arreglos los cuales me servirán como una memoria de cada dato
		 *presente en los txt con un largo de 300 según aparece en la pauta*/
		
		//Usuarios
		String[] UsuarioID = new String[300];
		String[] UsuarioContraseña = new String[300];
		
		//Registros
		String[] RegistroID = new String[300];
		String[] RegistroFecha = new String[300];
		int[] RegistroHoras = new int[300];
		String[] RegistroActividad = new String[300];
		
		/*Generar método el cual lea y rellene los Arreglos mediante lectura del txt de manera ordenada*/
		int a = 0; int b = 0;
		RellenarArreglo("Usuarios.txt", UsuarioID, UsuarioContraseña, RegistroID, RegistroFecha, RegistroHoras, RegistroActividad, a);
		RellenarArreglo("Registros.txt", UsuarioID, UsuarioContraseña, RegistroID, RegistroFecha, RegistroHoras, RegistroActividad, b);
		
		//Llamar al método menú principal para poder elegir entre los menús
		MenuPrincipal(UsuarioID, UsuarioContraseña, RegistroID, RegistroFecha, RegistroHoras, RegistroActividad, scanner);
		
		
		//cerrar scanner como una buena práctica :D
		scanner.close();
	
	}
	
	
	
	/* ------------------ Sección generada para la creación del menú global ------------------*/
	
	/*Método generado para gestionar el llamado de cada menú mediante la implementación 
	 *del Scanner y switch/case*/
	private static void MenuPrincipal(String[] usuarioID, String[] usuarioContraseña, String[] registroID,
			String[] registroFecha, int[] registroHoras, String[] registroActividad, Scanner scanner) {
		
		String Respuesta = "";
		
		while(!Respuesta.equals("3")) {
			
			Print("1) Menu de Usuarios\r\n"
					+ "2) Menu de Analisis\r\n"
					+ "3) Salir\n");
			
			Respuesta = scanner.nextLine();
		
			switch(Respuesta){
				
				case "1":
					Print("Opción 1\n");
					break;
					
				case "2":
					MenuAnalisis(usuarioID, usuarioContraseña, registroID, registroFecha, registroHoras, registroActividad, scanner);
					break;
					
				default:
					if(!Respuesta.equals("3")) {
						Print("Opción incorrecta, intente nuevamente\n");
					}
					break;
				
			}

		}
		
		Print("Saliento del programa .....");

	}

	/*--------------------------------------------------------------------------------------------------------*/
	
	
	
	
	
	/*------------------ Sección generada para la creación del menú de análisis ------------------*/
	
	/*Método generado para gestionar las distintas tareas pertenecientes al menú de análisis medienta
	 *preguntas de los scanner, ciclos y switch-case*/
	private static void MenuAnalisis(String[] usuarioID, String[] usuarioContraseña, String[] registroID,
			String[] registroFecha, int[] registroHoras, String[] registroActividad, Scanner scanner) {
		
		String Respuesta = "";
		
		while(!Respuesta.equals("5")) {
			
			Print("\nBienvenido al menu de analisis!\r\n"
				+ "\r\n"
				+ "Que deseas realizar?\r\n"
				+ "\r\n"
				+ "1) Actividad más realizada\r\n"
				+ "2) Actividad más realizada por cada usuario\r\n"
				+ "3) Usuario con mayor procastinacion\r\n"
				+ "4) Ver todas las actividades\r\n"
				+ "5) Salir\n");
			
			Respuesta = scanner.nextLine();
			
			String[] ActividadesUnicas = new String[300];
			ActividadesUnicas(ActividadesUnicas, registroActividad);
			
			switch(Respuesta) {
			
			case "1":
				ActividadMásRealizada(registroActividad, ActividadesUnicas, registroHoras);
				break;
				
			case "2":
				ActividadMásRealizadaPorUsuario(usuarioID, registroID, registroHoras, registroActividad, ActividadesUnicas);
				break;
				
			case "3":
				MásProcastinador(registroID, registroHoras, registroActividad, usuarioID);
				break;
				
			case "4":
				VerActividadesUnicas(ActividadesUnicas);
				break;
				
			default:
				if(!Respuesta.equals("5")) {
					Print("Opción incorrecta, intente nuevamente\n");
				}
				break;
			
			}
			
		}
		
		Print("Saliento del menú de Análisis .....\n");
		
	}
	
	
	//1
	
	/*Método el cual recorrerá Arreglos y almacenará el tiempo empleado por cada actividad para
	 *luego llamár al método del mayor e imprimir la actividad buscada*/
	private static void ActividadMásRealizada(String[] registroActividad, String[] actividadesUnicas,
			int[] registroHoras) {
		
		int[] ActividadMásRealizada = new int[actividadesUnicas.length];
		
		for(int a = 0; a < registroActividad.length; a++) {
			
			if(registroActividad[a] != null) {
				
				for(int b = 0; b < actividadesUnicas.length; b++) {
					
					if(actividadesUnicas[b] != null && actividadesUnicas[b].equals(registroActividad[a])) {
						
						ActividadMásRealizada[b] += registroHoras[a];
						break;
						
					}
					
				}
				
			}
			
		}
		
		int Posición = mayor(ActividadMásRealizada);
		Print("La actividad más realizada es: " +actividadesUnicas[Posición] + " con " + ActividadMásRealizada[Posición] + " Horas");
		
	}

	//2
	
	/*Método generado para mediante busqueda entre los arreglos de cada usuario, se encontrará
	 *la actividad más repetida por cada usuario empleando algoritmo del mayor y renovación de arreglos*/
	private static void ActividadMásRealizadaPorUsuario(String[] usuarioID, String[] registroID, int[] registroHoras,
			String[] registroActividad, String[] actividadesUnicas) {
		
		for(int a = 0; a < usuarioID.length; a++) {
			
			if(usuarioID[a] != null){

			int[] TiempoEnActividadFavorita = new int[registroActividad.length];
			
				for(int b = 0; b < registroActividad.length; b++) {
					
					if(registroActividad[b] != null && registroID[b] != null && registroID[b].equals(usuarioID[a])) {

						for(int c = 0; c < actividadesUnicas.length; c++) {
							
							if(actividadesUnicas[c] != null && actividadesUnicas[c].equals(registroActividad[b])) {
								
								TiempoEnActividadFavorita[c] += registroHoras[b];
								break;
								
							}
							
						}
						
					}
					
					
				}
				
				int Posición = mayor(TiempoEnActividadFavorita);
				Print("El usuario " + usuarioID[a] + " ha realizado la actividad " + actividadesUnicas[Posición] + "un total de " + TiempoEnActividadFavorita[Posición] + " horas");
			
			}
			
		}
		
	}

	//3
	
	/*Método el cual por cada usuario disponible, calculará el tiempo ocupado en actividades
	 *diferentes al estudio y las acumulará para luego llamar otra función y que calcule el mayor
	 *y mediante las listas anidades imprimirá al usuario más procastinador*/
	private static void MásProcastinador(String[] registroID, int[] registroHoras, String[] registroActividad, String[] usuarioID) {
		
		int[] TiempoDeOcio = new int[registroID.length];
		
		for(int a = 0; a < usuarioID.length; a++) {
			
			if(usuarioID[a] != null) {
				
				for(int b = 0; b < registroActividad.length; b++) {
					
					if(registroActividad[b] != null){
					
						if(!registroActividad[b].equals("estudiar") && registroID[b].equals(usuarioID[a])) {
							
							TiempoDeOcio[a] += registroHoras[b];
							
						}
					
					}
					
				}
				
			}
			
		}
		
		int Posición = mayor(TiempoDeOcio);
		
		Print("El usuario con mayor tiempo de ocio es: " + usuarioID[Posición] + ""
				+ " con " + TiempoDeOcio[Posición] + " Horas de tiempo de ocio");
		
	}

	//4

	/*Método el cual leerá e imprimirá la lista que se le ingrese*/
	private static void VerActividadesUnicas(String[] actividadesUnicas) {
		
		for(int a = 0; a < actividadesUnicas.length; a++) {
			
			if(actividadesUnicas[a] != null) {
				
				Print((a + 1) + ") " + actividadesUnicas[a]);
				
			}
			
		}
		
	}
	
	/*Método generado para buscar todas las actividades sin que se repitan ninguna vez y almacenar estas
	 *en un arreglo para su posterior uso*/
	private static void ActividadesUnicas(String[] actividadesUnicas, String[] registroActividad) {
		
		int Contador = 0;
		
		for(int a = 0; a < registroActividad.length; a++) {
			
			String Actividad = registroActividad[a];
			
			Boolean Encontrado = false;
			
			if(Actividad != null) {
				
				for(int b = 0; b < a; b++) {
					
					if(Actividad.equals(registroActividad[b])) {
						
						Encontrado = true;
						break;
						
					}
					
				}
				
				if(Encontrado == false) {
					actividadesUnicas[Contador] = Actividad;
					Contador++;
				}
				
			}
			
		}
		
	}
	
	
	//Más de 1 método usa este método
	
	/*Método el cual aplicará el algoritmo del mayor y retornará la posición de este*/
	private static int mayor(int[] tiempoDeOcio) {
		
		int mayor = 0;
		int posición = 0;
		
		for(int a = 0; a < tiempoDeOcio.length; a++) {
			
			if(tiempoDeOcio[a] >= mayor) {
				
				mayor = tiempoDeOcio[a];
				posición = a;
				
			}
			
		}
		
		return posición;
	}

	/*--------------------------------------------------------------------------------------------------------*/
	
	
	
	
	
	/* ------------------ Sección generada para lectura y almacenamiento de los datos de los txt ------------------*/

	/*Método empleado para generar una lectura de archivo en base al archivo que se le entregue
	 *comunicandose con otros método para almacenar cada dato en su Arreglo correspondiente*/
	private static void RellenarArreglo(String archivo, String[] usuarioID, String[] usuarioContraseña, 
			String[] registroID, String[] registroFecha, int[] registroHoras, String[] registroActividad, int posición) {
		
		File file = new File(archivo);
		
		try {
			
			Scanner lector = new Scanner(file);
			
			while(lector.hasNextLine()) {
				
				String linea = lector.nextLine();
				
				if(archivo.equals("Usuarios.txt")) {
					
					AlmacenarUsuario(linea, usuarioID, usuarioContraseña, posición);
					posición++;
					
				}
				
				else if (archivo.equals("Registros.txt")) {
					
					AlmacenarRegistros(linea, registroID, registroFecha, registroHoras, registroActividad, posición);
					posición++;
					
				}
			}
			
				lector.close();
				
		}
	
		catch(Exception e){
			
			Print("\nArchivo no encontrado\n");
			
		}
	}
	
	
	/*Método el cual mediante la entrega de una linea de texto, desglozará esta
	 *y guardará su información en su arreglo correspondiente*/
	public static void AlmacenarUsuario(String linea, String[] usuarioID, String[] usuarioContraseña, int posición) {

		String[] Partes = linea.split(";");
		
		usuarioID[posición] = Partes[0];
		usuarioContraseña[posición] = Partes[1];
		
	}
	
	/*Método el cual mediante la entrega de una linea de texto, desglozará esta
	 *y guardará su información en su arreglo correspondiente*/
	private static void AlmacenarRegistros(String linea, String[] registroID, String[] registroFecha, 
			int[] registroHoras, String[] registroActividad, int posición) {
		
		String[] Partes = linea.split(";");
		
		registroID[posición] = Partes[0];
		registroFecha[posición] = Partes[1];
		registroHoras[posición] = Integer.valueOf(Partes[2]);
		registroActividad[posición] = Partes[3];
		
	}
	
	/*----------------------------------------------------------------------------------------------------------------------*/
	
	//Método generado para imprimir bonito :D
	public static void Print(String texto) {
		System.out.println(texto);
	}
	
}

