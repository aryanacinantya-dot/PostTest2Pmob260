package aryana.posttest2
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // 1. Cari RecyclerView di layout
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_stories)


        // 2. Buat daftar data yang akan ditampilkan
        val storyDataList = ArrayList<Story>()
        storyDataList.add(
            Story(
                "Keindahan Tersembunyi Gunung Bromo",
                "Gunung Bromo di Jawa Timur menawarkan pemandangan matahari terbit yang megah...",
                "Aryana 232",
                R.drawable.gunung
            )
        )
        storyDataList.add(
            Story(
                "Resep Nasi Goreng Spesial Keluarga",
                "Nasi goreng adalah hidangan favorit sepanjang masa. Dengan resep ini, Anda bisa...",
                "Aryana 232",
                R.drawable.nasigoreng
            )
        )
        storyDataList.add(
            Story(
                "Smartphone Terbaru Rilis dengan Kamera 200MP",
                "Persaingan di pasar smartphone semakin sengit dengan diluncurkannya model terbaru...",
                "Aryana 232",
                R.drawable.iphone
            )
        )


        // 3. Buat instance dari Adapter dan masukkan datanya
        val adapter = StoryAdapter(storyDataList)


        // 4. Atur RecyclerView untuk menggunakan Adapter ini dan LayoutManager
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

data class Story(
    val title: String,
    val description: String,
    val author: String,
    val imageResId: Int
)


class StoryAdapter(private val storyList: List<Story>) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {


    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storyImage: ImageView = itemView.findViewById(R.id.story_image)
        val storyTitle: TextView = itemView.findViewById(R.id.story_title)
        val storyDescription: TextView = itemView.findViewById(R.id.story_description)
        val storyAuthor: TextView = itemView.findViewById(R.id.story_author)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gambar, parent, false)
        return StoryViewHolder(view)
    }


    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val currentStory = storyList[position]
        holder.storyImage.setImageResource(currentStory.imageResId)
        holder.storyTitle.text = currentStory.title
        holder.storyDescription.text = currentStory.description
        holder.storyAuthor.text = "Oleh: ${currentStory.author}"
    }


    override fun getItemCount(): Int {
        return storyList.size
    }
}