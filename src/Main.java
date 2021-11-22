import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        int quantidade = 10000;
        int[] vetor = new int[quantidade];

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = (int) (Math.random()*quantidade);
        }

        ManipuladorDeArquivos mp = new ManipuladorDeArquivos();
        File arquivo = mp.inicializa();
        mp.escreve(arquivo,"Vetor original: " + Arrays.toString(vetor), false);
        mp.escreve(arquivo,"", false);

        long tempoInicial = System.currentTimeMillis();
        int[] array = insertionSort(vetor);
        long tempoFinal = System.currentTimeMillis();

        mp.escreve(arquivo,"Vetor ordenado: " + Arrays.toString(array), false);
        mp.escreve(arquivo,"", false);
        mp.escreve(arquivo,"InsertionSort tempo decorrido: " + (tempoFinal - tempoInicial) +" ms", false);
        mp.escreve(arquivo,"", false);

        tempoInicial = System.currentTimeMillis();
        array = selectionSort(vetor);
        tempoFinal = System.currentTimeMillis();
        mp.escreve(arquivo,"SelectionSort tempo decorrido: " + (tempoFinal - tempoInicial) +" ms", false);
        mp.escreve(arquivo,"", false);

        tempoInicial = System.currentTimeMillis();
        array = bubbleSort(vetor);
        tempoFinal = System.currentTimeMillis();
        mp.escreve(arquivo,"BubbleSort tempo decorrido: " + (tempoFinal - tempoInicial) +" ms", false);
        mp.escreve(arquivo,"", false);

        tempoInicial = System.currentTimeMillis();
        array = quickSort(vetor, 0, vetor.length-1);
        tempoFinal = System.currentTimeMillis();
        mp.escreve(arquivo,"QuickSort tempo decorrido: " + (tempoFinal - tempoInicial) +" ms", false);
        mp.escreve(arquivo,"", false);
    }

    public static int[] insertionSort(int[] vetor) {
        int j;
        int key;
        int i;

        for (j = 1; j < vetor.length; j++)
        {
            key = vetor[j];
            for (i = j - 1; (i >= 0) && (vetor[i] > key); i--)
            {
                vetor[i + 1] = vetor[i];
            }
            vetor[i + 1] = key;
        }
        return vetor;
    }

    public static int[] bubbleSort(int vetor[]){
        boolean troca = true;
        int aux;
        while (troca) {
            troca = false;
            for (int i = 0; i < vetor.length - 1; i++) {
                if (vetor[i] > vetor[i + 1]) {
                    aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                    troca = true;
                }
            }
        }
        return vetor;
    }

    public static int[] selectionSort(int[] vetor) {
        for (int fixo = 0; fixo < vetor.length - 1; fixo++) {
            int menor = fixo;

            for (int i = menor + 1; i < vetor.length; i++) {
                if (vetor[i] < vetor[menor]) {
                    menor = i;
                }
            }
            if (menor != fixo) {
                int t = vetor[fixo];
                vetor[fixo] = vetor[menor];
                vetor[menor] = t;
            }
        }
        return vetor;
    }

    public static int[] quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }

        return vetor;
    }

    private static int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo)
                i++;
            else if (pivo < vetor[f])
                f--;
            else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }
}
