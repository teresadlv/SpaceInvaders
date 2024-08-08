package tp1;

//import static org.junit.jupiter.api.Assertions.fail;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import org.junit.jupiter.api.Test;

public class Tests {

//	private static boolean compareOutput(Path expectedPath, Path actualPath) throws FileNotFoundException, IOException {
//		boolean same = true;
//		try (BufferedReader expected = new BufferedReader(new FileReader(expectedPath.toFile()));
//				BufferedReader actual = new BufferedReader(new FileReader(actualPath.toFile()))) {
//
//			String expectedLine = expected.readLine();
//			String actualLine = actual.readLine();
//			int lineNumber = 1;
//			while (same && expectedLine != null && actualLine != null) {
//				same = expectedLine.equals(actualLine);
//				if (!same) {
//					System.out.println("Line: %d".formatted(lineNumber));
//					System.out.println("Expected: %s".formatted(expectedLine));
//					System.out.println("Actual: %s".formatted(actualLine));
//				}
//				expectedLine = expected.readLine();
//				actualLine = actual.readLine();
//				lineNumber++;
//			}
//
//			same = same && expectedLine == null && actualLine == null;
//		}
//		return same;
//	}
//
//	public void parameterizedTest(Path input, Path expected, Path output, String[] args) {
//		try (PrintStream out = new PrintStream(output.toFile()); InputStream in = new FileInputStream(input.toFile())) {
//			PrintStream oldOut = System.out;
//			InputStream oldIn = System.in;
//
//			System.setOut(out);
//			System.setIn(in);
//
//			Main.main(args);
//
//			System.setOut(oldOut);
//			System.setIn(oldIn);
//
//			if (!compareOutput(expected, output)) {
//				fail();
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			fail();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//			fail();
//		}
//	}
//
//	@Test
//	public void test00() {
//		parameterizedTest(Paths.get("tests/p3/00-easy_25-input.txt"), Paths.get("tests/p3/00-easy_25-expected.txt"),
//				Paths.get("tests/p3/00-easy_25-output.txt"), new String[] { "EASY", "25" });
//	}
//
//	@Test
//	public void test01() {
//		parameterizedTest(Paths.get("tests/p3/00-hard_25-input.txt"), Paths.get("tests/p3/00-hard_25-expected.txt"),
//				Paths.get("tests/p3/00-hard_25-output.txt"), new String[] { "HARD", "25" });
//	}
//
//	@Test
//	public void test02() {
//		parameterizedTest(Paths.get("tests/p3/00-insane_25-input.txt"), Paths.get("tests/p3/00-insane_25-expected.txt"),
//				Paths.get("tests/p3/00-insane_25-output.txt"), new String[] { "INSANE", "25" });
//	}
//
//	@Test
//	public void test03() {
//		parameterizedTest(Paths.get("tests/p3/01-insane_25-input.txt"), Paths.get("tests/p3/01-insane_25-expected.txt"),
//				Paths.get("tests/p3/01-insane_25-output.txt"), new String[] { "INSANE", "25" });
//	}
//	
//	@Test
//	public void test04() {
//		parameterizedTest(Paths.get("tests/p3/02-easy_25-input.txt"), Paths.get("tests/p3/02-easy_25-expected.txt"),
//				Paths.get("tests/p3/02-easy_25-output.txt"), new String[] { "EASY", "25" });
//	}
//	
//	@Test
//	public void test05() {
//		parameterizedTest(Paths.get("tests/p3/03-easy_25-erroresComandos-input.txt"), Paths.get("tests/p3/03-easy_25-erroresComandos-expected.txt"),
//				Paths.get("tests/p3/03-easy_25-erroresComandos-output.txt"), new String[] { "EASY", "25" });
//	}

}