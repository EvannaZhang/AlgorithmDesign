package ad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Point222{
	int x,y,z;
}
class Pair{
	double d;
	int d1,d2;
}

public class Closest {
	private static Point[] P;
	public static void main(String[] args){
		InputReader in = new InputReader(System.in);
	    PrintWriter out = new PrintWriter(System.out);
		int number = in.nextInt();
		int dimension = in.nextInt();
		P = new Point[number];
		for(int i=0;i<number;i++){
			P[i] = new Point();
		}
		if(dimension==1){
			int[] s = new int[number];
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i=0; i<number; i++){
				s[i] = in.nextInt();
				if(min>P[i].x)
					min = P[i].x;
				if(max<P[i].x)
					max = P[i].x;
			}
			Arrays.sort(P,xcompar);
			Pair d=Cpair(s,0,number-1);
			int fi = d.d1;
			int se = d.d2;
			if(fi>se){
				int temp = fi;
				fi = se;
				se = temp;
			}
			out.println(fi);
			out.println(se);
		}
		
		else if(dimension==2){
			for(int i=0; i<number; i++){
				P[i].x = in.nextInt();
				P[i].y = in.nextInt();
			}
		Point[] result = closest_pair(P);
		int x1 = result[0].x;
		int y1 = result[0].y;
		int x2 = result[1].x;
		int y2 = result[1].y;
		if(x1>x2){
			int temp1 = x1;
			int temp2 = y1;
			x1 = x2;
			y1 = y2;
			x2 = temp1;
			y2 = temp2;
		}
		else if(x1==x2 && y1>y2){
			int temp1 = x1;
			int temp2 = y1;
			x1 = x2;
			y1 = y2;
			x2 = temp1;
			y2 = temp2;
		}
		out.println(x1+" "+y1);
		out.println(x2+" "+y2);
		}
		else{ //dimension==3
			for(int i=0; i<number; i++){
				P[i].x = in.nextInt();
				P[i].y = in.nextInt();
				P[i].z = in.nextInt();
			}
			Arrays.sort(P,xcompar);
			Point[] answer = find3d(P);
			if(answer[0].x>answer[1].x){
				Point tmp = new Point();
				tmp = answer[0];
				answer[0] =answer[1];
				answer[1] = tmp;
			}
			else if(answer[0].x==answer[1].x && answer[0].y<answer[1].y){
				Point tmp = new Point();
				tmp = answer[0];
				answer[0] =answer[1];
				answer[1] = tmp;
			}else if(answer[0].x==answer[1].x && answer[0].y==answer[1].y && answer[0].z > answer[1].z){
				Point tmp = new Point();
				tmp = answer[0];
				answer[0] =answer[1];
				answer[1] = tmp;
			}
			out.println(answer[0].x+" "+answer[0].y+" "+answer[0].z);
			out.println(answer[1].x+" "+answer[1].y+" "+answer[1].z);
		}
		out.close();
	}
	
	public static double min3d(Point x, Point y){
		return Math.pow(Math.pow(x.x-y.x, 2)+Math.pow(x.y-y.y, 2)+Math.pow(x.z-y.z, 2), 0.5);
	}
	
	public static Point[] find3d(Point[] p){
		if(p.length<=3){
			if(p.length<2)
				return null;
			Point first = new Point(), second = new Point();
			double dismin = Double.MAX_VALUE;
			for(int i=0; i<p.length; i++){
				for(int j=i+1; j<p.length;j++){
					if(dismin>min3d(p[i],p[j])){
						dismin = min3d(p[i],p[j]);
						first = p[i];
						second = p[j];
					}
				}
			}
			return new Point[]{first,second};
		}else{
			Point[] l = Arrays.copyOfRange(p, 0, p.length/2+1);  
			Point[] r = Arrays.copyOfRange(p, p.length/2+1, p.length);
			Point[] L = find3d(l);
			Point[] R = find3d(r);
			double ldis;
			if(L==null){
				ldis = Double.MAX_VALUE;
			}
			else if(L.length<=1){
				ldis = Double.MAX_VALUE;
			}
			else
				ldis= min3d(L[0],L[1]);
			
			double rdis;
			if(R==null){
				rdis = Double.MAX_VALUE;
			}
			else if(R.length<=1)
				rdis = Double.MAX_VALUE;
			else
				rdis = min3d(R[0],R[1]);
			
			double delta = Math.min(ldis,rdis);
			Point minFirstPoint = null, minSecondPoint =null;
			if(delta==ldis){
				minFirstPoint = L[0];
				minSecondPoint = L[1];
			}
			else{
				minFirstPoint = R[0];
				minSecondPoint = R[1];
			}
			
			int midx = p[p.length/2+1].x;
			ArrayList<Point> Stmp = new ArrayList<Point>();
			for(int i=0;i<p.length;i++){
				if(Math.abs(p[i].x-midx)<delta){
					Stmp.add(p[i]);
					}
			}
					Point[] S = Stmp.toArray(new Point[0]);
					Point[] Sy = S.clone();
					Arrays.sort(Sy,ycompar);
					double secondMinDistance = Double.MAX_VALUE;
					Point tmpminFirst = null;
					Point tmpminSecond = null;
					for(int i=0;i<Sy.length;i++){
						Point basep = Sy[i];
						for(int j=i+1;j<Sy.length;j++){
							Point compp = Sy[j];
							if((compp.y-basep.y < 2*delta)&&Math.abs(compp.x-basep.x)<2*delta && Math.abs(compp.z-basep.z)<2*delta){	//如果在15个点之内，即x和y坐标相差都在2*delta之内	
								double tmpdis = min3d(basep,compp);
								if(secondMinDistance>tmpdis){
									secondMinDistance = tmpdis;
									tmpminSecond = compp;
									tmpminFirst = basep;
								}
							}
							else{
								break;
							}
						}
					}
					if(secondMinDistance<delta){
						return new Point[]{tmpminFirst,tmpminSecond};
					}
					else{
						return new Point[]{minFirstPoint,minSecondPoint};
					}
			
		}
	}

	public static int Max(int s[],int l,int r){
		int s_max=s[l];
		for(int i=l+1;i<=r;i++)
			if(s_max<s[i])
				s_max=s[i];
		return s_max;
	}
	 
	public static int Min(int s[],int l,int r){
		int s_min=s[l];
		for(int i=l+1;i<=r;i++)
			if(s_min>s[i])
				s_min=s[i];
		return s_min;
	}
	
	public static Pair Cpair(int s[],int l,int r){
		Pair min_d = new Pair();
		min_d.d = Integer.MAX_VALUE;
		min_d.d1 = 0;
		min_d.d2 = 0;
		
		if(r-l<1) return min_d;
		int m1= Max(s,l,r), m2=Min(s,l,r);
		int m=(m1+m2)/2;
		int j = Partition(s,m,l,r);
		Pair d1=Cpair(s,l,j),d2=Cpair(s,j+1,r);
		int p=Max(s,l,j), q=Min(s,j+1,r);
		if(d1.d<d2.d){
			if((q-p)<d1.d){
				min_d.d=(q-p);
				min_d.d1=q;
	            min_d.d2=p;
				return min_d;
			}
			else return d1;
		}
		else{
			if((q-p)<d2.d){
				min_d.d=(q-p);
				min_d.d1=q;
	            min_d.d2=p;
				return min_d;
			}
			else return d2;
		}

	}

	public static void Swap(int s[],int x,int y){
		int temp = s[x];
		s[x] = s[y];
		s[y] = temp;
	}
	 
	
	public static int Partition(int s[],int x,int l,int r){
		int i = l - 1,j = r + 1;
		while(true){
			while(s[++i]<x && i<r);
			while(s[--j]>x);
			if(i>=j){
				break;
			}
			Swap(s,i,j);
		}
		return j;
	}
	
	private static Comparator<Point> ycompar = new Comparator<Point>() { 
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.y<o2.y){
				return -1;
			}
			else if(o1.y>o2.y){
				return 1;
			}
			return 0;
		}
	};
	private static Comparator<Point> xcompar = new Comparator<Point>() {  	//根据x坐标比较的比较器
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.x<o2.x){
				return -1;
			}
			else if(o1.x>o2.x){
				return 1;
			}
			return 0;
		}
	};
	static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
        
        public char[] nextCharArray() {
            return next().toCharArray();
        }
        
        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }
        
        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }
        
    }
	public static Point[] closest_pair(Point[] points) {
		Point[] px = points.clone();
		Arrays.sort(px,xcompar);
		Point[] py = points.clone();
		Arrays.sort(py,ycompar);
		Point[] result = closest_pair_rec(px,py);
		return result;
	}
	
public static Point[] closest_pair_rec(Point[]px,Point[]py){
		Point minFirstPoint = new Point();
		Point minSecondPoint = new Point();
		double mindistance = 0;
		if(px.length<=3){
			double mindist = Double.MAX_VALUE;
			int mini = 0;
			int minj = 0;
			for(int i=0;i<px.length;i++){
				for(int j=i+1;j<px.length;j++){
					if(mindist>distance(new Point[]{px[i],px[j]})){
						mindist = distance(new Point[]{px[i],px[j]});
						mini = i;
						minj = j;
					}
				}
			}
			return new Point[]{px[mini],px[minj]};
		}
		Point[] Q = Arrays.copyOfRange(px, 0, px.length/2+1);  
		Point[] R = Arrays.copyOfRange(px, px.length/2+1, px.length);
		Point[] Qx = Q.clone();
		Arrays.sort(Qx, xcompar);
		Point[] Qy = Q.clone();
		Arrays.sort(Qy, ycompar);
		
		Point[] Rx = R.clone();
		Arrays.sort(Rx, xcompar);
		Point[] Ry = R.clone();
		Arrays.sort(Ry, ycompar);
		
		Point[] q = closest_pair_rec(Qx,Qy);
		Point[] r = closest_pair_rec(Rx,Ry);
		
		double qd;
		if(Q.length>1)
			qd= distance(q);
		else
			qd = Integer.MAX_VALUE;
		double rd;
		if(R.length>1)
			rd = distance(r);
		else
			rd = Integer.MAX_VALUE;
		double delta = Math.min(qd,rd);
		if(delta==qd){
			minFirstPoint = q[0];
			minSecondPoint = q[1];
		}
		else{
			minFirstPoint = r[0];
			minSecondPoint = r[1];
		}
		mindistance = delta;
		
		int maxx = Qx[Qx.length-1].x;
		ArrayList<Point> Stmp = new ArrayList<Point>();
		for(int i=0;i<px.length;i++){
			if(Math.abs(px[i].x-maxx)<delta){
				Stmp.add(px[i]);
			}
		}
				Point[]S = Stmp.toArray(new Point[0]);
				Point[] Sy = S.clone();
				Arrays.sort(Sy,ycompar);
				double secondMinDistance = Double.MAX_VALUE;
				Point tmpminFirst = null;
				Point tmpminSecond = null;
				for(int i=0;i<Sy.length;i++){
					Point basep = Sy[i];
					for(int j=i+1;j<Sy.length;j++){
						Point compp = Sy[j];
						if((compp.y-basep.y < 2*delta)&&Math.abs(compp.x-basep.x)<2*delta){	
							double tmpdis = distance(new Point[]{basep,compp});
							if(secondMinDistance>tmpdis){
								secondMinDistance = tmpdis;
								tmpminSecond = compp;
								tmpminFirst = basep;
							}
						}
						else{
							break;
						}
					}
				}
				if(secondMinDistance<mindistance){
					return new Point[]{tmpminFirst,tmpminSecond};
				}
				else{
					return new Point[]{minFirstPoint,minSecondPoint};
				}
			}

	private static double distance(Point[] q) {
		return Math.pow(Math.pow(q[0].x-q[1].x,2)+Math.pow(q[0].y-q[1].y,2), 0.5);
	}
	
}
