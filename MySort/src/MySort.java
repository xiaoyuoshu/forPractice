
public class MySort {
	//²åÈëÅÅĞò
	protected static void insertSort(int[] nums){
		for(int i = 1; i < nums.length ;i++){
			int temp = nums[i];
			int j;
			for(j = i; j > 0 && temp < nums[j-1]; j--){
				nums[j] = nums[j-1];
			}
			nums[j] = temp;
		}
	}
	
	//Ñ¡ÔñÅÅĞò
	protected static void selectSort(int[] nums){
		for (int i = 0; i < nums.length; i++) {
			int tag = i, temp;
			for (int j = i; j < nums.length; j++) {
				if(nums[tag] > nums[j]) tag = j;
			}
			temp = nums[i];
			nums[i] = nums[tag];
			nums[tag] = temp;
		}
	}
	
	//¶ÑÅÅĞò
	protected static void heapSort(int[] nums) {
		for(int i = nums.length/2; i >= 0; i--){
			heapAdjust(nums, i, nums.length);
		}
		for(int i = nums.length - 1; i > 0; i--){
			int temp = nums[0];
			nums[0] = nums[i];
			nums[i] = temp;
			heapAdjust(nums, 0, i);
		}
	}
	private static void heapAdjust(int[] nums, int begin, int length) {
		int left = begin * 2 + 1;
		int right = left + 1;
		int largest = begin;
		if (left < length && nums[left] > nums[begin]) {
			largest = left;
		}
		if (right < length && nums[right] > nums[largest]) {
			largest = right;
		}
		if (begin != largest) {
			int temp = nums[begin];
			nums[begin] = nums[largest];
			nums[largest] = temp;
			heapAdjust(nums, largest, length);
		}
	}
	
	//¿ìËÙÅÅĞò
	protected static void quickSort(int[] nums) {
		oneQuickSort(nums, 0, nums.length - 1);
	}
	private static void oneQuickSort(int[] nums, int low, int high) {
		if(low < high){
			int temp = Part(nums, low, high);
			oneQuickSort(nums, low, temp - 1);
			oneQuickSort(nums, temp + 1, high);
		}
	}
	private static int Part(int[] nums, int low, int high) {
		int temp = nums[low];
		while(low < high){
			while(low < high && nums[high] >= temp) high--;
			nums[low] = nums[high];
			while(low < high && nums[low] <= temp) low++;
			nums[high] = nums[low];
		}
		nums[low] = temp;
		return low;
	}
}
