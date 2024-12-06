import java.util.Arrays;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class App {

    // Classe para armazenar os dados do benchmark
    static class BenchmarkData {
        String algoritmo;
        String cenario;
        long tempoExecucao;

        BenchmarkData(String algoritmo, String cenario, long tempoExecucao) {
            this.algoritmo = algoritmo;
            this.cenario = cenario;
            this.tempoExecucao = tempoExecucao;
        }
    }

    // Algoritmos de Ordenação para inteiros

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void quickSortIterativo(int[] arr) {
        int n = arr.length;
        int[] stack = new int[n];
        int top = -1;

        stack[++top] = 0;
        stack[++top] = n - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];

            int pi = partition(arr, low, high);

            if (pi - 1 > low) {
                stack[++top] = low;
                stack[++top] = pi - 1;
            }

            if (pi + 1 < high) {
                stack[++top] = pi + 1;
                stack[++top] = high;
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                int temp = arr[++i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i], j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // Algoritmos de Ordenação para strings

    public static void bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(String[] arr) {
        if (arr.length <= 1) return;
        int mid = arr.length / 2;
        String[] left = Arrays.copyOfRange(arr, 0, mid);
        String[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(String[] arr, String[] left, String[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i].compareTo(right[j]) <= 0) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void quickSortIterativo(String[] arr) {
        int n = arr.length;
        int[] stack = new int[n];
        int top = -1;

        stack[++top] = 0;
        stack[++top] = n - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];

            int pi = partition(arr, low, high);

            if (pi - 1 > low) {
                stack[++top] = low;
                stack[++top] = pi - 1;
            }

            if (pi + 1 < high) {
                stack[++top] = pi + 1;
                stack[++top] = high;
            }
        }
    }

    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                String temp = arr[++i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void shellSort(String[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap].compareTo(temp) > 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // Geradores de Vetores

    public static String[] gerarVetorStringsAleatorias(int tamanho) {
        String[] vetor = new String[tamanho];
        Random random = new Random();
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < tamanho; i++) {
            StringBuilder palavra = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                palavra.append(caracteres.charAt(random.nextInt(caracteres.length())));
            }
            vetor[i] = palavra.toString();
        }
        return vetor;
    }

    // Medir tempo de execução
    public static long medirTempo(String[] vetor, Runnable algoritmo) {
        long inicio = System.nanoTime();
        try {
            algoritmo.run();
        } catch (OutOfMemoryError e) {
            System.err.println("Erro de memória ao executar algoritmo: " + e.getMessage());
            return -1; // Retorna -1 se ocorrer um erro de memória
        }
        long fim = System.nanoTime();
        return (fim - inicio) / 1000; // Retorna o tempo em microssegundos
    }

    // Salvar os resultados no arquivo CSV
    public static void salvarResultadosCSV(List<BenchmarkData> resultados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultados_500K_5x.csv"))) {
            writer.write("Algoritmo,Cenario,TempoExecucao\n");
            for (BenchmarkData data : resultados) {
                writer.write(data.algoritmo + "," + data.cenario + "," + data.tempoExecucao + "\n");
            }
            System.out.println("Resultados salvos em resultados_benchmark.csv");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os resultados: " + e.getMessage());
        }
    }

    // Executar testes com N execuções
    public static void executarTestes(String[] vetor, String nomeCenario, int numExecucoes, List<BenchmarkData> resultados) {
        for (int i = 1; i <= numExecucoes; i++) {
            System.out.println("\nExecução " + i + " - " + nomeCenario);

            // Reutilizando os vetores e resetando-os
            String[] vetorBubble = Arrays.copyOf(vetor, vetor.length);
            String[] vetorMerge = Arrays.copyOf(vetor, vetor.length);
            String[] vetorQuick = Arrays.copyOf(vetor, vetor.length);
            String[] vetorShell = Arrays.copyOf(vetor, vetor.length);

            long tempoBubble = medirTempo(vetorBubble, () -> bubbleSort(vetorBubble));
            long tempoMerge = medirTempo(vetorMerge, () -> mergeSort(vetorMerge));
            long tempoQuick = medirTempo(vetorQuick, () -> quickSortIterativo(vetorQuick));
            long tempoShell = medirTempo(vetorShell, () -> shellSort(vetorShell));

            if (tempoBubble != -1) resultados.add(new BenchmarkData("Bubble Sort", nomeCenario, tempoBubble));
            if (tempoMerge != -1) resultados.add(new BenchmarkData("Merge Sort", nomeCenario, tempoMerge));
            if (tempoQuick != -1) resultados.add(new BenchmarkData("Quick Sort", nomeCenario, tempoQuick));
            if (tempoShell != -1) resultados.add(new BenchmarkData("Shell Sort", nomeCenario, tempoShell));

            // Sugestão de coleta de lixo após cada execução para liberar memória
            System.gc();
        }
    }

    // Main
    public static void main(String[] args) {
        int numExecucoes = 5; // Número de execuções
        int[] tamanhos = {500000}; // Tamanho para o teste (ajustado para evitar sobrecarga de memória)

        List<BenchmarkData> resultados = new ArrayList<>();

        for (int tamanho : tamanhos) {
            System.out.println("Executando testes com vetor de tamanho " + tamanho);

            String[] vetorCrescente = gerarVetorStringsAleatorias(tamanho);  // Vetor de strings aleatórias
            String[] vetorDecrescente = gerarVetorStringsAleatorias(tamanho);
            String[] vetorAleatorio = gerarVetorStringsAleatorias(tamanho);

            System.out.println("Executando cenários de ordenação com " + numExecucoes + " execuções cada.");
            executarTestes(vetorCrescente, "Cenário 1 - Vetor ordenado crescente", numExecucoes, resultados);
            executarTestes(vetorDecrescente, "Cenário 2 - Vetor ordenado decrescente", numExecucoes, resultados);
            executarTestes(vetorAleatorio, "Cenário 3 - Vetor aleatório", numExecucoes, resultados);

            // Adicionando o novo cenário de strings aleatórias
            String[] vetorStringsAleatorias = gerarVetorStringsAleatorias(tamanho);
            executarTestes(vetorStringsAleatorias, "Cenário 4 - Vetor de strings aleatórias", numExecucoes, resultados);
        }

        // Salvar os resultados no CSV
        salvarResultadosCSV(resultados);
    }
}
