package algorithms.fundamentals;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import algorithms.Fundamentals.Chapter1;
import algorithms.Fundamentals.Counter;
import algorithms.StdLib.In;
import algorithms.StdLib.StdIn;
import algorithms.StdLib.StdOut;

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
	/**
	 * 1.1.16测试
	 */
	@Test
	public void testExR1(){
		System.out.println(Chapter1.exR1(6));
	}
	
	/**
	 * 1.1.18测试
	 */
	@Test
	public void testMystery(){
		System.out.println(Chapter1.mystery(2, 25));
		System.out.println(Chapter1.mystery(3, 11));
	}
	/**
	 * 1.1.19测试,会耗费很长时间
	 * 
	 */
	@Test
	public void testF(){
		for (int i = 0; i <100 ; i++) {
			StdOut.println(i + " " + Chapter1.f(i));
		}
	}
	/**
	 * 1.1.20
	 * 
	 */
	@Test
	public void testLn(){
		for (int i = 0; i <100 ; i++) {
			StdOut.println(Chapter1.ln(i));
		}
	}
	/**
	 * 1.1.21
	 * 测试不成功，一直在输入不能跳出循环
	 */
	@Test
	public void testOut(){
		Chapter1.out();
	}
	/**
	 * 1.1.22
	 * UNIX 平台，绝对路径名的前缀始终是 "/",windows是"\",可用 file.separator代替
	 * java找文件是在项目根目录下开始找
	 */
	@Test
	public void testRank(){
		In inw = new In("./src/test/java/algorithms/fundamentals/tinyW");
		In in = new In("./src/test/java/algorithms/fundamentals/tinyT");
		int[] whitelist = inw.readAllInts();
		int[] key = in.readAllInts();
        Arrays.sort(whitelist);

        // read key; print if not in whitelist
        for (int i = 0; i < key.length; i++) {
            if (Chapter1.rank(key[i],whitelist ) == -1)
                StdOut.println("不存在"+key[i]);
		}
	}
	/**
	 * 1.1.24测试
	 */
	@Test
	public void testEuclid(){
		System.out.println("----p----|----q----");
		StdOut.printf("最大公约数："+Chapter1.euclid(1111111, 1234567));
	}
	/**习题1.1.27测试
	 *  java binomial 10  5 0.5:         1,233 calls
     *  java binomial 20 10 0.5:     1,216,535 calls
     *  java binomial 30 15 0.5: 1,219,164,498 calls
	 */
	@Test
	public void testBinomial(){
		Counter counter = new Counter("calls");
		System.out.println(Chapter1.binomial(20, 10, 0.5, counter));
		System.out.println(counter.toString());
	}
	/**习题1.1.27b测试
	 *  java binomialB 10   5 0.5:         50 calls
     *  java binomialB 20  10 0.5:     	  175 calls
     *  java binomialB 30  15 0.5: 		  375 calls
     *  java binomialB 100 50 0.5:       3875 calls
	 */
	@Test
	public void testBinomialB(){
		Counter counter = new Counter("calls");
		System.out.println(Chapter1.binomialB(100, 50, 0.5, counter));
		System.out.println(counter.toString());
	}
	/**
	 * 1.1.28去重测试
	 */
	@Test
	public void testDel(){
		In inw = new In("./src/test/java/algorithms/fundamentals/tinyW");
		int[] whitelist = inw.readAllInts();
        Arrays.sort(whitelist);

        for (int t : whitelist) {
            StdOut.print(" " + t);
        }
        StdOut.println();
        int[] res = Chapter1.del(whitelist);

        for (int t : res) {
            StdOut.print(" " + t);
        }
	}
}
