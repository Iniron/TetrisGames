package Ex;

public class LayoutEx_GameController {
	
	private final LayoutEx_ViewController cn;
	
	public LayoutEx_GameController(LayoutEx_ViewController c) {
		// TODO Auto-generated constructor stub
		this.cn = c;
		cn.addPanel(20,10);
		cn.createbrick();
	}
	
}
