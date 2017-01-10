package hitesh.asimplegame;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "teste";
	// tasks table name
	private static final String TABLE_QUEST = "quest_teste";
	// tasks Table Columns names
	private static final String KEY_ID = "qid";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; // correct option
	private static final String KEY_OPTA = "opta"; // option a
	private static final String KEY_OPTB = "optb"; // option b
	private static final String KEY_OPTC = "optc"; // option c
	private static final String KEY_OPTD = "optd"; // option d
	private SQLiteDatabase dbase;

	public QuizHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase = db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
				+ KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT, " + KEY_OPTD + " TEXT " +")";
		db.execSQL(sql);
		addQuestion();
		// db.close();
	}

	private void addQuestion() {
		Question q1 = new Question("Qual é a formula da molecular da água?", "CO2", "II2", "O2", "H2O", "CO2");
		this.addQuestion(q1);
		Question q2 = new Question("Fala-se em Cuba e pensamos no país, mas é tambem nome de uma localidade.", "No Alentejo", "No Ribatejo", "No Algarve","No Douro", "No Alentejo");
		this.addQuestion(q2);
        Question q3 = new Question("10-3 = ?", "6", "7", "8","8", "7");
        this.addQuestion(q3);
        Question q4 = new Question("5+7 = ?", "12", "13", "14","8", "12");
        this.addQuestion(q4);
        Question q5 = new Question("3-1 = ?", "1", "3", "2","8", "2");
        this.addQuestion(q5);
        Question q6 = new Question("0+1 = ?", "1", "0", "10","8", "1");
        this.addQuestion(q6);
        Question q7 = new Question("9-9 = ?", "0", "9", "1","8", "0");
        this.addQuestion(q7);
        Question q8 = new Question("3+6 = ?", "8", "7", "9","8", "9");
        this.addQuestion(q8);
        Question q9 = new Question("1+5 = ?", "6", "7", "5","8", "6");
        this.addQuestion(q9);
        Question q10 = new Question("7-5 = ?", "3", "2", "6","8", "2");
        this.addQuestion(q10);
        Question q11 = new Question("7-2 = ?", "7", "6", "5","8", "5");
        this.addQuestion(q11);
        Question q12 = new Question("3+5 = ?", "8", "7", "5","8", "8");
        this.addQuestion(q12);
        Question q13 = new Question("0+6 = ?", "7", "6", "5","8", "6");
        this.addQuestion(q13);
        Question q14 = new Question("12-10 = ?", "1", "2", "3","8", "2");
        this.addQuestion(q14);
        Question q15 = new Question("12+2 = ?", "14", "15", "16","8", "14");
        this.addQuestion(q15);
        Question q16 = new Question("2-1 = ?", "2", "1", "0","8", "1");
        this.addQuestion(q16);
        Question q17 = new Question("6-6 = ?", "6", "12", "0","8", "0");
        this.addQuestion(q17);
        Question q18 = new Question("5-1 = ?", "4", "3", "2","8", "4");
        this.addQuestion(q18);
        Question q19 = new Question("4+2 = ?", "6", "7", "5","8", "6");
        this.addQuestion(q19);
        Question q20 = new Question("5+1 = ?", "6", "7", "5","8", "6");
        this.addQuestion(q20);
        Question q21 = new Question("5-4 = ?", "5", "4", "1","8", "1");
        this.addQuestion(q21);
		// END
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}

	// Adding new question
	public void addQuestion(Question quest) {
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION());
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());

		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);
	}

	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase = this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) do {
            Question quest = new Question();
            quest.setID(cursor.getInt(0));
            quest.setQUESTION(cursor.getString(1));
            quest.setANSWER(cursor.getString(2));
            quest.setOPTA(cursor.getString(3));
            quest.setOPTB(cursor.getString(4));
            quest.setOPTC(cursor.getString(5));
            quest.setOPTD(cursor.getString(6));


            quesList.add(quest);
        } while (cursor.moveToNext());
		// return quest list
		return quesList;
        //quest.close();dbase.close();
	}


}
