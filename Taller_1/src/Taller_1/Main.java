package Taller_1;

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
			
			Print("Bienvenido al menu de analisis!\r\n"
				+ "\r\n"
				+ "Que deseas realizar?\r\n"
				+ "\r\n"
				+ "1) Actividad más realizada\r\n"
				+ "2) Actividad más realizada por cada usuario\r\n"
				+ "3) Usuario con mayor procastinacion\r\n"
				+ "4) Ver todas las actividades\r\n"
				+ "5) Salir\n");
			
			Respuesta = scanner.nextLine();
			
			switch(Respuesta) {
			
			case "1":
				Print("Opción 1\n");
				break;
				
			case "2":
				Print("Opción 2\n");
				break;
				
			case "3":
				Print("Opción 3\n");
				break;
				
			case "4":
				Print("Opción 4\n");
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

