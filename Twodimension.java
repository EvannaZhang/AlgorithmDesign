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

class Point{
	int x,y,z;
}

public class Twodimension {
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
			for(int i=0; i<number; i++){
				P[i].x = in.nextInt();
			}
			Arrays.sort(P,xcompar);
			Point[] d = new Point[2];
			d =find1d(P);
			int fi = d[0].x;
			int se = d[1].x;
			if(fi>se){
				int temp = fi;
				fi = se;
				se = temp;
			}
			out.println(fi);
			out.println(se);
		}else if(dimension==2){
			for(int i=0; i<number; i++){
				P[i].x = in.nextInt();
				P[i].y = in.nextInt();
			}
			Arrays.sort(P,xcompar);
			Point[] result = find2d(P);
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
			else if(answer[0].x==answer[1].x && answer[0].y>answer[1].y){
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
		if(x==null||y==null)
			return Double.MAX_VALUE;
		return Math.pow(Math.pow(x.x-y.x, 2)+Math.pow(x.y-y.y, 2)+Math.pow(x.z-y.z, 2), 0.5);
	}
	public static double min2d(Point a, Point b) {
		if(a==null||b==null)
			return Double.MAX_VALUE;
		return Math.pow(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2), 0.5);
	}
	public static double min1d(Point a, Point b){
		if(a==null||b==null)
			return Double.MAX_VALUE;
		return Math.abs(a.x - b.x);
	}
	
	public static Point[] find1d(Point[] P){
		if(P==null)
			return null;
		if(P.length<=3){
			if(P.length<=1){
				return null;
			}
			else if(P.length==2){
				return new Point[]{P[0],P[1]};
			}
			else{ //3
				double a = min1d(P[0],P[1]);
				double b = min1d(P[1],P[2]);
				if(a<=b){
					return new Point[]{P[0],P[1]};
				}
				else
					return new Point[]{P[1],P[2]};
			}
		}else{
			int mid = P.length/2;
			Point[] left = Arrays.copyOfRange(P, 0, mid);
			Point[] right = Arrays.copyOfRange(P, mid, P.length);
			Point[] l = find1d(left);
			Point[] r = find1d(right);
			double leftdis,rightdis;
			if(l==null){
				leftdis = Double.MAX_VALUE;
			}
			else if(l.length<2){
				leftdis = Double.MAX_VALUE;
			}
			else{
				leftdis = min1d(l[0],l[1]);
			}
			if(r==null){
				rightdis = Double.MAX_VALUE;
			}
			else if(r.length<2){
				rightdis = Double.MAX_VALUE;
			}
			else{
				rightdis = min1d(r[0], r[1]);
			}
			double delta = Math.min(leftdis, rightdis);
			double middle = Double.MAX_VALUE;
			if(left!=null&&right!=null)
				middle = Math.abs(left[left.length-1].x-right[0].x);
			delta = Math.min(delta, middle);
			if(delta==leftdis){
				return new Point[]{l[0],l[1]};
			}
			else if(delta==rightdis){
				return new Point[]{r[0],r[1]};
			}
			else{
				return new Point[]{left[left.length-1],right[0]};
			}
		}
	}
	
	public static Point[] find2d(Point[] p){
		if(p==null)
			return null;
		if(p.length<=3){
			if(p.length<2)
				return null;
			Point first = new Point(), second = new Point();
			double dismin = Double.MAX_VALUE;
			for(int i=0; i<p.length; i++){
				for(int j=i+1; j<p.length;j++){
					if(dismin>min2d(p[i],p[j])){
						dismin = min2d(p[i],p[j]);
						first = p[i];
						second = p[j];
					}
				}
			}
			return new Point[]{first,second};
		}else{ //>3
			Point[] l = Arrays.copyOfRange(p, 0, p.length/2+1);  
			Point[] r = Arrays.copyOfRange(p, p.length/2+1, p.length);
			Point[] L = find2d(l);
			Point[] R = find2d(r);
			double ldis;
			if(L==null){
				ldis = Double.MAX_VALUE;
			}
			else if(L.length<=1){
				ldis = Double.MAX_VALUE;
			}
			else
				ldis= min2d(L[0],L[1]);
			
			double rdis;
			if(R==null){
				rdis = Double.MAX_VALUE;
			}
			else if(R.length<=1)
				rdis = Double.MAX_VALUE;
			else
				rdis = min2d(R[0],R[1]);
			
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
							if((compp.y-basep.y < 2*delta) && Math.abs(compp.x-basep.x)<2*delta ){
								double tmpdis = min2d(basep,compp);
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
	
	public static Point[] find3d(Point[] p){
		if(p==null)
			return null;
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
	private static Comparator<Point> xcompar = new Comparator<Point>() { 
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

}

