public class Matrix {
	private int _sizem, _sizen;
	private int[][] _mat;
	
	// Constructeur initialisant une matrice n*m avec la valeur val
	public Matrix(int m, int n, int val) {
		_sizem=m;
		_sizen=n;
		_mat=new int[m][n];
		for (int i=0; i<_sizem; ++i) 
		for (int j=0; j<_sizen; ++j) _mat[i][j]=val;
	}
	
	// Fonction d'acces :
	public void set(int m, int n, int val) { _mat[m][n]=val; }
	public int get(int m, int n) { return _mat[m][n]; }

	// Constructeur de recopie :
	public Matrix(Matrix m) {
		_sizem=m._sizem;
		_sizen=m._sizen;
		_mat=new int[_sizem][_sizen];	
		for (int i=0; i<_sizem; ++i) 
		for (int j=0; j<_sizen; ++j) _mat[i][j]=m._mat[i][j];
	}
}

