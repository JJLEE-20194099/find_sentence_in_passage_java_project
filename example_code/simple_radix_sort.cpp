#include <iostream>
using namespace std;

int getMaxArr(int array[], int n) {
  int max = array[0];
  for (int i = 1; i < n; i++)
    if (array[i] > max)
      max = array[i];
  return max;
}

void distributionSort(int array[], int size, int place) {
  const int max = 10;
  int output[size];
  int count[max];

  for (int i = 0; i < max; ++i)
    count[i] = 0;

  
  for (int i = 0; i < size; i++)
    count[(array[i] / place) % 10]++;

  
  for (int i = 1; i < max; i++)
    count[i] += count[i - 1];

  
  for (int i = size - 1; i >= 0; i--) {
    output[count[(array[i] / place) % 10] - 1] = array[i];
    count[(array[i] / place) % 10]--;
  }

  for (int i = 0; i < size; i++)
    array[i] = output[i];
}


void radixsort(int array[], int size) {
  
  int max = getMaxArr(array, size);

  for (int place = 1; max / place > 0; place *= 10)
    distributionSort(array, size, place);
}

void printArray(int array[], int size) {
  int i;
  for (i = 0; i < size; i++)
    cout << array[i] << " ";
  cout << endl;
}

int main() {
  int array[] = {570, 821, 752, 563, 744, 925, 166, 817, 638, 639};
  int n = sizeof(array) / sizeof(array[0]);
  radixsort(array, n);
  printArray(array, n);
}