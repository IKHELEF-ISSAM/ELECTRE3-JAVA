package multicritere;


import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class Main {
	
		public static float somme_tableau(float[] tab) {
		float s = 0;
		for (int i = 0; i < tab.length; i++) {
			s = s+tab[i];
		}
		return s;
	}
		public static double[][] multiplication_matrice(double[][] m1, float[][] m2) {
			double[][] m_multi = new double[m1.length][m2.length];
			for (int i = 0; i < m1.length; i++) {
				for (int j = 0; j < m1[0].length; j++) {
					m_multi[i][j] = m1[i][j] * m2[i][j];
				}
			}
			return m_multi;
		}

		public static double[][] transposee_matrice(double[][] matrice) {
			double[][] matriceT = new double[matrice[0].length][matrice.length];
			for (int i = 0; i < matrice.length; i++) {
				for (int j = 0; j < matrice[0].length; j++) {
					matriceT[j][i] = matrice[i][j];
				}
			}
			return matriceT;
		}
	    public static void tri_par_colonne_decr(double arr[][], int col) 
	    {  
	        Arrays.sort(arr, new Comparator<double[]>() { 
	            
	          @Override              
	           public int compare(final double[] entry1,  
	                             final double[] entry2) { 
	            if (entry1[col] < entry2[col]) 
	                return 1; 
	            else
	                return -1; 
	          } 
	        });  
	    } 
	    public static void tri_par_colonne_cro(double arr[][], int col) 
	    {  
	        Arrays.sort(arr, new Comparator<double[]>() { 
	            
	          @Override              
	           public int compare(final double[] entry1,  
	                             final double[] entry2) { 
	            if (entry1[col] > entry2[col]) 
	                return 1; 
	            else
	                return -1; 
	          } 
	        });  
	    } 
	    
		public static float div_tab(float[] val , float[] poids) {
			float valDiv = somme_tableau(val) / somme_tableau(poids);
			return valDiv;
		} 
		public static float valeur_in(int a, int b , float c , float d, float e) {
			float v = (((a-b) - d)/(e-d))*c;
			return v;
		}
		
		public static float[][] matrice_concordance(int[][] tab, float[] poids, float[] tabP, float[] tabI) {
			int l = tab.length;
			float[][] val_conc     = new float[l][l];
			float[]   val = new float[tab[0].length];
			

					for (int i = 0; i < l; i++) {
						for (int j = 0; j < l; j++) {
							if (i == j) {
								val_conc[i][j] = 1;
							} else {
								for (int k = 0; k < tab[0].length; k++) {
									if (tab[j][k] - tab[i][k]>= tabP[k] ) {val[k] = 0 ;}	
									if (tab[j][k] - tab[i][k]<= tabI[k]  ) {val[k] = poids[k];}
									if ((tab[j][k] - tab[i][k]) < tabP[k] && (tab[j][k] - tab[i][k]) > tabI[k]) {
										val[k] = valeur_in(tab[j][k] , tab[i][k], poids[k], tabP[k], tabI[k]);
										
									}
								}
								val_conc[i][j] = div_tab(val,poids);
							}
						}
					}
					return val_conc;
				}

	public static double[][] matrice_discordance(int[][] tab, float[] tabP, float[] tabV , int k) {
		int l = tab.length;
		double[][] val_disco = new double[l][l];
		
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				if ((i == j)) {
					val_disco[i][j] = 0.0;
				} else {	
					if(tab[j][k] - tab[i][k] <= tabP[k] ) {val_disco[i][j]=0.0;}
					if (tab[j][k] - tab[i][k] >= tabV[k] ) {val_disco[i][j] = 1.0;}
					if ((tab[j][k] - tab[i][k]) > tabP[k] && (tab[j][k] - tab[i][k]) < tabV[k]) {
						val_disco[i][j] = valeur_in(tab[j][k],tab[i][k],1,tabP[k],tabV[k]);	
					}
				}
			}
		}
		return val_disco;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Entrer le nombre de projets :");
		int nb_pro=input.nextInt();
		
		System.out.println("Entrer le nombre de criteres :");
		int nb_cri=input.nextInt();
		float[] tab_poids =new float [nb_cri] ;
		float tab_p[]     = new float [nb_cri];
		float tab_v[]     = new float [nb_cri];
		float t_indif[]   = new float [nb_cri];
		float somme_poids = 0;	
		int t_perf[][]	  =new int [nb_pro][nb_cri];
		float conc[][]	  =new float [nb_pro][nb_pro];
		double[][] Cred   = new double[nb_pro][nb_pro];
		double disco[][]  =new double [nb_pro][nb_pro];
		
		
		System.out.println("--------Entrer les poids---------");
		
		for(int i=0;i<nb_cri;i++) {tab_poids[i]=input.nextInt();};
				
		System.out.println();
		
		for(int x=0; x<nb_cri; x++) {somme_poids= somme_poids +tab_poids[x];}
		System.out.println("Entrer les préferences");
		for(int i=0;i<nb_cri;i++){ tab_p[i]=input.nextInt();};
	
		System.out.println("Entrer les indifférences");
		for(int i=0;i<nb_cri;i++){ t_indif[i]=input.nextInt();};
								
		
	    System.out.println("Entrer les veto");
		for(int i=0;i<nb_cri;i++){tab_v[i]=input.nextInt();};
							
		System.out.println();
		
		System.out.println("Entrer les valeurs de la matrice  performance");
		for(int i=0;i<nb_pro;i++){
			for(int j=0;j<nb_cri;j++){t_perf[i][j]=input.nextInt();}	
		}
		
		System.out.println(" La matrice de performance : ");
	
		for(int u=0;u<nb_pro;u++){
			for(int y=0;y<nb_cri;y++){
				System.out.print(t_perf[u][y]+"	");
			}
			System.out.println();
		
			conc =  matrice_concordance(t_perf,  tab_poids,  tab_p,  t_indif);
		
			for (int q = 0; q < t_perf[0].length; q++) {
			disco = matrice_discordance(t_perf, tab_p, tab_v, q);
			}
	
	float[][] matrice_conc    = matrice_concordance(t_perf, tab_poids, tab_p, t_indif);
	int k = 0;
	for (int j = 0; j < t_perf.length; j++) {
		for (int i = 0; i < t_perf.length; i++) {
			Cred[i][j] = 1;
		}
	}
	for (k = 0; k < t_perf[0].length; k++) {
		double[][] matrice_disco = matrice_discordance(t_perf, tab_p, tab_v, k);
		for (int j = 0; j < t_perf.length; j++) {
			for (int i = 0; i < t_perf.length; i++) {
				if (i != j){
					if (matrice_disco[i][j] <= matrice_conc [i][j]) {
						matrice_disco[i][j] = 1;
					} else {
						matrice_disco[i][j] = (1 - matrice_disco[i][j]) / (1 - matrice_conc [i][j]);
					}
					Cred[i][j] = Cred[i][j] * matrice_disco[i][j];
				} else{
					Cred[i][j] = 1;
				}
			}
		}
	}
	Cred = multiplication_matrice(Cred,conc);
}
		
				System.out.println();	
				System.out.println("La matrice de concordance : ");	
				for(int i=0; i<nb_pro;i++){
					for(int j=0; j<nb_pro;j++){
						conc[i][j] =(float) Double.parseDouble(new DecimalFormat("##.###").format(conc[i][j]));
						System.out.print(conc[i][j]+"	");	
					}
					System.out.println();
				}
				System.out.println();
				System.out.println("La matrice de discordance : ");
				
				
				for(int a=0; a<nb_pro;a++){
					for(int b=0; b<nb_pro;b++){
						disco[a][b] =(double) Double.parseDouble(new DecimalFormat("##.###").format(disco[a][b]));
						System.out.print(disco[a][b]+"	");	
					}
					System.out.println();
				}
				System.out.println();
				System.out.println("La matrice de credibilite : ");
				System.out.println("****************************");
				
				for(int i=0; i<nb_pro;i++){
					for(int j=0; j<nb_pro;j++){
						Cred[i][j] = (double) Double.parseDouble(new DecimalFormat("##.###").format(Cred[i][j]));
						System.out.print(Cred[i][j]+"  ");	
					}
					System.out.println();
				}
				System.out.println();
				System.out.println("Entrer la valeur de credibilite :");
				System.out.println("*****************************");
				double taux=input.nextDouble();
				
				for (int i = 0; i < Cred.length; i++) {
					for (int j = 0; j < Cred.length; j++) {
						if (taux - Cred[i][j] < 0 ){
							Cred[i][j] = 1;
							
						}else
						if (taux - Cred[i][j] == 0 )  {
							Cred[i][j] = 1;
							
						}
					else Cred[i][j] = 0;
					}
				
				}
				System.out.println();	
				System.out.println("la matrice aprés selection de valeur de crédibilité: ");
				System.out.println("****************************");
				double suml[]=new double [Cred.length];
				double sumc[]=new double [Cred.length];
				double diff[]=new double [Cred.length];
				double tableau[][]=new double [2][Cred.length];
				double tran[][]=new double [2][Cred.length];
				
				
				
				for(int i=0; i<nb_pro;i++){
					for(int j=0; j<nb_pro;j++){
						Cred[i][j] = (float) Double.parseDouble(new DecimalFormat("##.###").format(Cred[i][j]));
						System.out.print(Cred[i][j]+"	      ");	
					}
					System.out.println();
				}
				for(int i =0; i< Cred.length; i++) {
					for (int j = 0; j < Cred.length; j++)
					{
						
					double w = Cred[i][j];
					suml[i] =  suml[i] +  w;
					}
				}
				for(int j=0; j< Cred.length; j++) {
					for (int i= 0; i < Cred.length; i++)
					{
						
					double w = Cred[i][j];
					sumc[j] =  sumc[j] +  w;
					}
				}
				System.out.println();	
				System.out.println("Somme des colonnes: ");
				
				System.out.println();
			
				for(int k=0; k<Cred.length;k++){
				
						System.out.println(sumc[k]+"	      ");	}
				System.out.println();
				
				System.out.println("Somme des lignes: ");
				System.out.println();
				
				for(int g=0; g<Cred.length;g++){
					
					System.out.println(suml[g]+"	      ");	}
				System.out.println("Difference: ");
				System.out.println();
		for(int g=0; g<Cred.length;g++){
					diff[g]=suml[g]-sumc[g];
			
				}
		for(int g=0; g<Cred.length;g++){
			
			System.out.println(diff[g]+"	      ");	}
				
				
				
				
				
				
				System.out.println("*** *** *** *** ***");	
				  for (int i = 0;i < tableau.length; i++) {
				         for (int j = 0;j < tableau[i].length;j++) {
				            if (i == 0) {tableau[i][j] = j+1;}
				            else {tableau[i][j] = diff[j];}
				       
				            System.out.print(tableau[i][j] + " ");
				         }
				         System.out.println();
				      }
				      System.out.println();
				   
			

				        System.out.print("\n\n Pour la distillation descendante:\n");
				   
				         tran = transposee_matrice(tableau);
				         for (int i = 0; i < tableau[0].length; i++) {
				                for (int j = 0; j < tableau.length; j++) {
				                	tri_par_colonne_decr(tran, 1); 
				                	// tri_par_colonne(tableau, 1);
				                 //   System.out.print("Projet numero  "+tran[i][j] + "===> ");
				                }
				              
				                
				            }
				         for (int i = 0; i < tran.length; i++) {
				            
				                	
				              System.out.print("Projet numero  "+tran[i][0] + "===> ");
				            
				                
				            }
				       
				     
			                System.out.print("\n");
			                
			                System.out.print("\n\n Pour la distillation ascendante:\n");
							   
					         tran = transposee_matrice(tableau);
					         for (int i = 0; i < tableau[0].length; i++) {
					                for (int j = 0; j < tableau.length; j++) {
					                	tri_par_colonne_cro(tran, 1); 
					               
					                }
					        
					              
					               
					            }
					         for (int i = 0; i < tran.length; i++) {
						            
				                	
					              System.out.print("Projet numero  "+tran[i][0] + "===> ");
					            
					                
					            }
					           System.out.print("\n");
					           System.out.print("\n");
					           
					         for (int i =0; i <nb_pro ; i++) {
					        	 for (int j =0; j <nb_pro; j++) {
					       
					        	 if(tableau[1][i]==tableau[1][j] && (i!=j)) {
						            
				                	int m=i+1;
				                	int kk=j+1;
					              System.out.print("Le projet "+ kk  + " et le projet  "+ m + "  se valent.");

							         System.out.print("\n");
					        	  }
					        
					            }		     
					     	}            
					       
					       
					  
						         System.out.print("\n");
						       
							     
					             
							     
						}            
					               
	
}