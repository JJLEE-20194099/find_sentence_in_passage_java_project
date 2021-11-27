#include<iostream>
using namespace std;

int main() {
    int arr[] = {-10, 4, 3, 5, 1, 1, 2, 4, 5, 6, 7, 2, -1};
    int n = sizeof(arr) / sizeof(arr[0]);
    
    int max_ele = arr[0];
    int min_ele = arr[0];
    for(int i = 1; i < n; i++) {
        if (max_ele < arr[i]) max_ele = arr[i];
        if (min_ele > arr[i]) min_ele = arr[i];
    }

    int M = max_ele - min_ele;

    int count_ele[M + 1];
    for (int i = 0; i <= M; i++) count_ele[i] = 0;

    for (int i = 0; i < n; i++) {
        count_ele[arr[i] - min_ele]++;
    }

    for (int i = 1; i <= M; i++) {
        count_ele[i] = count_ele[i - 1] + count_ele[i];
    }
    
    int res[n];

    for(int i = n - 1; i >= 0; i--) {
        res[count_ele[arr[i] - min_ele] - 1] = arr[i];
        count_ele[arr[i] - min_ele]--;
    }

    for (int i = 0; i < n; i++) arr[i] = res[i];

    for (int i = 0; i < n; i++) cout << arr[i] << endl;
}