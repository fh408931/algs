package algorithms.Fundamentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import algorithms.StdLib.StdIn;
import algorithms.StdLib.StdOut;

/*
 * 第一章基础第一部分基础编程模型
 * 书中列子和习题
 */
public class Chapter1 {
	/*1.1.9习题十进制转二进表示并转换为字符串
	 * 没有实现转字符串（可以不用第归直接取到的余拼接字符串在反转）
	 * 十进制转换成二进制取余到序排列
	 * 使用第归方式实现到序输出得到二进制数字
	 */
	public static void  binaryNum(int a){
		if(a/2>0)  binaryNum(a/2);
		System.out.print(a%2);		 
	}
	/*
	 * 1.1.13题 M行N列二维数组交换行和列
	 */
	public static int[][] format(int[][] a){
		int[][] b = new int[a[0].length][a.length];
		for(int i=0;i<a[0].length;i++){
			for(int j=0;j<a.length;j++){
				b[i][j]=a[j][i];
			}
		}
		return b;
	}
	/*
	 * 1.1.13题 后续方法打印交换后的数组
	 */
	public static void print(int[][] matrix) {
        for(int[] line:matrix){
            for(int site:line){
                System.out.print("    "+site);
            }
            System.out.println();
        }
    }
	
	/*
	 * 二分查找第归	
	 */
	public static int rankLoop(int key,int[] a){
		int lo = 0;
		int hi = a.length-1;
		while(lo<=hi){
			int mid = lo+(hi-lo)/2;
			if(key>a[mid]){
				lo = mid+1;
			}else if(key<a[mid]){
				hi = mid-1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	/*
	 * 1.1.14 lg方法返回log2N的最大整数
	 */
	public static int lg(int n){
		int base = 2;
		int max = 0;
		while(n>1){
			max++;
			n/=base;
		}
		return max;
	}
	
	/**1.1.15
	 * @param a 整形数组
 	 * @param m 整数m
	 * @return 返回大小等于m的数组，其中第i个元素的值
	 * 		   为整数i在参数数组中出现的次数
	 *二分法查找到对应数字后数字可能有重复
	 *使用两个for循环找到前后相等数字（要考虑数组益出）
	 *循环结束后break结束while
	 *注释的是使用循环方法
	 *
	 */
	public static int[] histogram(int[] a,int m){
		/*int[] result = new int[m];
		for (int i = 0; i < a.length; i++) {
			if (a[i] >= 0 && a[i] <m) {
				result[a[i]]++;
			}
		}*/
	
		int[] arr = new int[m];
		Arrays.sort(a);	
		for(int i=0;i<m;i++){
			int lo = 0;
			int hi = a.length-1;
			int key = 0;
			while(lo<=hi){
				int mid= lo+(hi-lo)/2;
				if(i>a[mid]){
					lo = mid+1;
				}else if(i<a[mid]){
					hi = mid-1;
				}else{
					key++;
					if(mid<a.length-1){
						for (int j = mid+1; j<=a.length-1&&a[j]==a[mid]; j++) {
							key++;
						}
					}
					if(mid>0){
						for (int k = mid-1; k>=0&&a[k]==a[mid]; k--) {
							key++;
						}
					}
					break;
				}
			}
			arr[i] = key;
		}
		return arr;
	}
	/**习题1.1.16
	 * @param n 整型int
	 * @return 311361142246
	 * 
	 */
	public static String exR1(int n){
		if(n<=0) return "";
		return exR1(n-3) + n + exR1(n-2) + n;
	}
	/**习题1.1.18
	 * @param n
	 * @return 总结公式a*([2^0]+[2^1]+.....+[2^n])
	 *[]表示数据在b%2==0时2的n次方不存在；
	 *0...n指数
	 *题目中+替换*return0 变return1 原理一样
	 */
	public static int mystery(int a,int  b){
		if(b==0) return 0;
		if(b%2==0) return mystery(a+a,b/2);
		return mystery(a+a,b/2)+a;
	}
	
	/**习题1.1.19
	 * @param n
	 * @return
	 */
	public static long f(int n){
		if(n==0) return 0;
		if(n==1) return 1;
		return f(n-1)+f(n-2);
	} 
	/**习题1.1.20
	 * @param n
	 * @return
	 */
	public static long ln(int n){
		if(n==0) return 1;
		return n*ln(n-1);
	}
	/**
	 * 习题1.1.21 
	 */
	public static void out(){
		StdOut.println("请按以下格式输入");
		StdOut.printf("%s,%d,%d\n", "name", 5, 10);
		StdOut.printf("%s,%d,%d\n", "name", 6, 8);
		List<String> list = new ArrayList<>();
		String in;
		while((in = StdIn.readLine())!="bye"){
			if(in=="bye") break;
			list.add(in);
		}
		System.out.print("  姓名|  击中|  总数|   成功率|\n");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String[] str = string.split(",");
			int a = Integer.parseInt(str[1]);
			int b = Integer.parseInt(str[2]);
			System.out.printf("|%4s|%4d|%4d|%6.3f|\n", str[0], a, b,(float)a/b);
		}
	}
	/**习题1.1.22
	 * @return
	 */
	public static int rank(int key, int[] a){
		return rank(key,a,0,a.length-1,0);
	}
	public static int rank(int key,int[] a,int lo,int hi,int indent){
		if(lo>hi) return -1;
		int mid = lo + (hi-lo)/2;
		StdOut.printf("%s%-4d%d\n", repeat(4*indent++, ' '), lo, hi);
		if(a[mid]>key){
			return rank(key,a,lo,mid-1,indent);
		}else if(a[mid]<key){
			return rank(key,a,mid+1,hi,indent);
		}else{
			return mid;
		}
	}
	private static String repeat(int n, char c){
        String s = "";
        for (int i = 0; i < n; i++)
            s += c;
        return s;
    }
}
