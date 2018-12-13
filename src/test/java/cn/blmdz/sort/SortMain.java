package cn.blmdz.sort;

import com.alibaba.fastjson.JSON;

public class SortMain {
    public static void main(String[] args) {
        int[] arr = {6,2,7,3,8,9};
        new Quick().sort(arr, 0, 5);
        
    }

    static class Quick1 {
        
    }

    static class Quick {
        public void sort(int arr[], int low, int high) {
            int l = low;
            int h = high;
            int povit = arr[low];

            while (l < h) {
                while (l < h && arr[h] >= povit)
                    h--;
                if (l < h) {
                    int temp = arr[h];
                    arr[h] = arr[l];
                    arr[l] = temp;
                    l++;
                }

                while (l < h && arr[l] <= povit)
                    l++;

                if (l < h) {
                    int temp = arr[h];
                    arr[h] = arr[l];
                    arr[l] = temp;
                    h--;
                }
            }
            System.out.println(JSON.toJSONString(arr));
            System.out.println("l=" + (l + 1) + " h=" + (h + 1) + " povit=" + povit);
            if (l > low)
                sort(arr, low, l - 1);
            if (h < high)
                sort(arr, l + 1, high);
        }
    }
}
