package algs;

import org.junit.Test;

import algorithms.basic.Chapter1;

/*
 * 第一章基础第一部分基础编程模型
 * 书中列子和习题方法测试
 */
public class Chapter1Test {
	/*
	 * 1.1.13题 M行N列二维数组交换行和列
	 */
	@Test
	public void testSwap() {
		int[][] a ={{1,2},{3,4},{5,6},{7,8}};
		int[][] b = Chapter1.format(a);
		Chapter1.print(b);
	}
	/*
	 * 书中demo二分查找循环二分查找第归
	 */
	@Test
	public void testSearch() {
		int[] a = {1,5,10,22,35,57,98};
		System.out.println(Chapter1.rank(22,a));
		System.out.println(Chapter1.rankLoop(35,a));
	}
	/*
	 * 1.1.9习题十进制转二进表示并转换为字符串测试
	 */
	@Test
	public void testBinaryNum() {
		int[] a = {13,55,34,26,105};
		for(int i=0;i<a.length;i++){
			Chapter1.binaryNum(a[i]);
			System.out.println();
		}
	}
	/*
	 * 1.1.14 lg方法返回log2N的最大整数
	 */
	@Test
	public void  testMaxLog() {
		System.out.println(Chapter1.lg(2049));
	}
	
	/**1.1.15
	 * a 整形数组
 	 * m 整数m
	 * 返回大小等于m的数组，其中第i个元素的值
	 * 为整数i在参数数组中出现的次数测试
	 */
	@Test
	public void testHistogram(){
		int[] a = {1,1,2,3,1,4,3,5,6,4};
		int m = 4;
		int[] arr = Chapter1.histogram(a, m);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
		int[] b = {0,1,2,3};
		int[] arrTwo = Chapter1.histogram(b, m);
		for (int i = 0; i < arrTwo.length; i++) {
			System.out.print(arrTwo[i]);
		}
 	}
	@Test
	public void testExR1(){
		System.out.println(Chapter1.exR1(6));
	}
	@Test
	public void testMystery(){
		System.out.println(Chapter1.mystery(2, 25));
		System.out.println(Chapter1.mystery(3, 11));
	}
}
