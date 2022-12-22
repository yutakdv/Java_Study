import javax.swing.JOptionPane;
import java.util.*;

public class SecretMessage {
	
	/** sizeOfBoard - 문장을 길이를 받아서 문장을 채우기 충분한 정사각형 보드의 최소 크기를 리턴 
	 * length - 문장의 길이 
	 * @return 정사각형 보드의 크기 */
	private int sizeOfBoard(int length) {
		double size = Math.sqrt(length);
		if (size == (int)size)
			return (int)size;
		else
			return (int)size + 1;
	}
	/** isInKey - 문자열 key에 문자 c가 있는지 검사 
	 * c - 찾을 문자
	 * key - 문자열
	 * @return key에서 c의 존재 여부 */
	private boolean isInKey(char c, String key) {
		for (int i = 0; i < key.length(); i++) {
			if (c == key.charAt(i)) 
				return true;
		}
		return false;
	}
	
	/** pickOneRandomly - 문자열 key에서 무작위로 문자 하나 선택하여 리턴 
	 * key - 대상 문자열
	 * @return 무작위로 선택한 문자 */
	private char pickOneRandomly(String key) {
		int index = (int)(Math.random() * key.length());
		return key.charAt(index);
	}
	
	/** encode - 평문 plain과 비밀키 key를 가지고 암호문을 만들어 리턴 
	 * plain - 평문 문자열 
	 * key - 비밀키 문자열 
	 * @return 암호문 문자열 */
	public String encode(String plain, String key) {
		// plain 문자열을 문자의 배열로 변환 
		char[] text = plain.toCharArray();
		
		// plain을 담을 수 있는 정사각형 보드 크기 결정
		int size = sizeOfBoard(plain.length());
		
		// 정사각형 보드 생성 
		char[][] board = new char[size][size];
		
		// 정사각형 보드를 차례로 채움 - 빈칸은 key에서 문자를 무작위로 뽑아 채움 
		int tmp = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (tmp < text.length) {
					if (text[tmp] == '\s') {
						board[i][j] = pickOneRandomly(key);
						tmp++;
					}
					else {
						board[i][j] = text[tmp];
						tmp++;
					}
				} else {
					board[i][j] = pickOneRandomly(key);
				}
			}
		}
		
		// 정사각형 보드를 세로로 읽어 암호문 생성 
		String encoded = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				encoded += board[j][i];
			}
		}
		
		return encoded;
	}
	
	/** decode - 암호문 secret과 비밀키 key를 가지고 평문으로 복호화 하여 리턴 
	 * secret - 암호문 문자열 
	 * key - 비밀키 문자열 
	 * @return 평문 문자열 */
	public String decode(String secret, String key) {
		// secret 문자열을 문자의 배열로 변환 
		char[] text = secret.toCharArray();
		
		// plain을 담을 수 있는 정사각형 보드 크기 계산 
		int size = (int)(Math.sqrt(secret.length()));
		
		// 정사각형 보드 생성 
		char[][] board = new char[size][size];
		
		// 정사각형 보드를 암호문으로 채움
		int tmp = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (tmp < text.length) {
					if (Character.compare(text[tmp], '진') == 0 || Character.compare(text[tmp], '달') == 0 || Character.compare(text[tmp], '래') == 0) {
						board[j][i] = '\s';
						tmp++;
					} else {
						board[j][i] = text[tmp];
						tmp++; 
					}
				}
			}
		}
		
		// 정사각형 보드를 세로로 읽어 평문 복호화 (key에 속한 문자는 빈칸으로 대체)
		String decoded = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				decoded += board[i][j];
			}
		}
		
		return decoded;
	}
	
	public static void main(String[] args) {
		String sentence = JOptionPane.showInputDialog("암호화할 문장을 주세요.");
		String key = JOptionPane.showInputDialog("비밀키를 주세요.");
		SecretMessage sm = new SecretMessage();
		System.out.println(sentence);		
		String encoded = sm.encode(sentence, key);
		System.out.println(encoded);
		String decoded = sm.decode(encoded, key);
		System.out.println(decoded);
	}
}



