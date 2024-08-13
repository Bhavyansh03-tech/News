package com.example.news.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.adapter.ApiAdapter
import com.example.news.adapter.MyAdapter
import com.example.news.adapter.ThirdAdapter
import com.example.news.api.RetrofitInstance
import com.example.news.databinding.FragmentBreakingNewsBinding
import com.example.news.apiModels.Article
import com.example.news.apiModels.NewsResponse
import com.example.news.models.BreakingModel
import com.example.news.models.PhotoModel
import com.example.news.models.RecentModel
import com.example.news.models.SetUpModel
import com.example.news.utils.Constants
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

class BreakingNewsFragment : Fragment() {

    private lateinit var binding: FragmentBreakingNewsBinding
    private lateinit var adapter: ApiAdapter
    private lateinit var myAdapter: MyAdapter
    private lateinit var thirdAdapter: ThirdAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)

        val photoList = ArrayList<PhotoModel>()
        photoList.add(PhotoModel(R.drawable.img_15, "", "", "", "", "https://www.hindustantimes.com/entertainment/bollywood/aaliyah-kashyap-announces-engagement-shows-off-huge-ring-father-anurag-kashyap-bff-janhvi-kapoor-react-101684581907175.html",1,1,1,1,"Anurag Kashya's daughter Aaliyah Kashyap announces engagement, flaunts huge ring - Hindustan Times","","","","", "16 April 2023", Constants.PHOTO))
        photoList.add(PhotoModel(R.drawable.img_16, "", "", "", "","https://timesofindia.indiatimes.com/tv/news/hindi/shark-tank-indias-aman-gupta-walks-the-red-carpet-at-cannes-2023-with-wife-priya-dagar-says-had-always-seen-aishwarya-rai-or-other-celebs-walk-if-i-can-so-can-you/photostory/100379101.cms",1,1,1,1,"Shark Tank India's Aman Gupta walks the red carpet at Cannes 2023 with wife Priya Dagar; says, 'Had always seen Aishwarya Rai or other celebs walk, if I can so can you'","","","","",  "16 April 2023", Constants.PHOTO))
        photoList.add(PhotoModel(R.drawable.img_17, "", "", "", "","https://timesofindia.indiatimes.com/entertainment/hindi/bollywood/news/ibrahim-ali-khan-completes-his-first-film/articleshow/100379123.cms",1,1,1,1,"Ibrahim Ali Khan completes his first film ","","","","",  "16 April 2023", Constants.PHOTO))
        photoList.add(PhotoModel(R.drawable.img_18, "", "", "", "","https://www.ndtv.com/entertainment/adipurush-song-jai-shri-ram-prabhas-sets-on-a-journey-to-bring-kriti-sanon-back-4051420",1,1,1,1,"Adipurush Song Jai Shri Ram: Prabhas Sets On A Journey To Bring Kriti Sanon Back","","","","",  "16 April 2023", Constants.PHOTO))
        val card = ArrayList<PhotoModel>()
        card.add(PhotoModel(1,"https://www.gsmarena.com/motorola_razr_40_ultra_razr_40_plus_usa-news-58621.php", "https://tech.hindustantimes.com/tech/news/iphone-user-cyberstalkers-using-windows-11-tool-to-spy-on-you-71684586750925.html", "https://www.sportskeeda.com/gta/best-tuner-cars-drifting-gta-online-may-2023", "http://beebom.com/samsung-wont-replace-google-search-with-bing-report/","", R.drawable.img_19,R.drawable.img_20,R.drawable.img_21,R.drawable.img_22,"","Motorola Razr 40 Ultra to launch as Razr+ in the US, marketing materials surface","iPhone user? Cyberstalkers using Windows 11 tool to spy on you","5 best Tuner cars for drifting in GTA Online","Samsung Reportedly Dropping Plans To Substitute Google Search With Bing - Beebom",  "", Constants.CARD))
        val linear = ArrayList<PhotoModel>()
        linear.add(PhotoModel(R.drawable.img_7, "", "", "", "","https://www.hindustantimes.com/technology/scientists-stanford-university-california-develop-e-skin-sense-of-touch-101684584734611.html",1,1,1,1,"1. Scientists develop e-skin, claim it could give same sense of touch as real one","","","","",  "16 April 2023", Constants.LINEAR))
        linear.add(PhotoModel(R.drawable.img_6, "", "", "", "","https://www.space.com/spacex-iridium-oneweb-launch-may-2023",1,1,1,1,"2. Watch SpaceX launch satellites for OneWeb, Iridium after abort today for free","","","","",  "16 April 2023", Constants.LINEAR))
        linear.add(PhotoModel(R.drawable.img_5, "", "", "", "","https://www.youtube.com/watch?v=eKZ32C2RLf0",1,1,1,1,"3. NASA'S Perseverance Rover Captures New Images of Ancient River Evidence on Mars","","","","",  "16 April 2023", Constants.LINEAR))
        linear.add(PhotoModel(R.drawable.img_4, "", "", "", "","https://www.jagranjosh.com/general-knowledge/new-genome-data-challenges-traditional-view-of-human-evolution-1684580794-1",1,1,1,1,"4. New Genome Data Challenges Traditional View of Human Evolution","","","","",  "16 April 2023", Constants.LINEAR))

        val thirdList = ArrayList<SetUpModel>()
        thirdList.add(SetUpModel("Entertainment",Constants.PHOTO, photoList))
        thirdList.add(SetUpModel("Technology" ,Constants.CARD, card))
        thirdList.add(SetUpModel("Science" ,Constants.LINEAR, linear))
        newRyc(thirdList)

        // Photo List :->
        val pList = ArrayList<RecentModel>()
        pList.add(RecentModel(R.drawable.img_10, "https://techcrunch.com/2023/04/21/missouri-trans-snitch-form-down-after-people-spammed-it-with-the-bee-movie-script/", "Missouri trans 'snitch form' down after people spammed it with the 'Bee Movie' script", "16 April 2023", Constants.PIC))
        pList.add(RecentModel(R.drawable.img_11, "https://techcrunch.com/2023/04/21/as-ai-eliminates-jobs-a-way-to-keep-people-afloat-financially-thats-not-ubi/", "As AI eliminates jobs, a way to keep people afloat financially (that's not UBI)", "16 April 2023", Constants.PIC))
        pList.add(RecentModel(R.drawable.img_12, "https://techcrunch.com/2023/04/22/this-week-in-apps-apple-sherlocks-journaling-apps-twitters-checkmark-apocalypse-snap-summit-recap/", "This Week in Apps: Apple 'sherlocks' journaling apps, Twitter's checkmark apocalypse, Snap summit recap", "16 April 2023", Constants.PIC))
        pList.add(RecentModel(R.drawable.img_13, "https://techcrunch.com/2023/04/22/ai-generated-chart-toppers-apple-gets-into-savings-and-microsoft-drops-twitter/", "AI-generated chart toppers, Apple gets into savings, and Microsoft drops Twitter", "16 April 2023", Constants.PIC))
        pList.add(RecentModel(R.drawable.img_14, "https://techcrunch.com/2023/04/21/tesla-wins-autopilot-crash-case-in-california/", "Tesla wins Autopilot crash case in California","16 April 2023", Constants.PIC))

        // Recent List :->
        val rList = ArrayList<RecentModel>()
        rList.add(RecentModel(R.drawable.img, "https://www.youtube.com/watch?v=ALp5zu9G_Is","Apple launches its high-yield savings account with 4.15% interest rate | World Business Watch | WION - WION", "16 April 2023", Constants.RECENT))
        rList.add(RecentModel(R.drawable.img_1, "https://www.cnbc.com/2023/04/18/apple-opens-first-store-in-india-in-iphone-sales-manufacturing-push.html","Apple opens its first store in India as it looks for a new generation of iPhone users - CNBC", "16 April 2023", Constants.RECENT))
        rList.add(RecentModel(R.drawable.img_2, "https://www.foxnews.com/politics/desantis-says-anheuser-busch-too-woke-has-lost-him-customer-why-would-you-drink-bud-light","DeSantis says Anheuser-Busch is too woke, has lost him as a customer: 'Why would you want to drink Bud Light?' - Fox News", "16 April 2023", Constants.RECENT))
        rList.add(RecentModel(R.drawable.img_3, "https://www.teslarati.com/volkswagen-id-7-drivetrain-435-mile-range/","Volkswagen ID.7 features new the MEB drivetrain with 435 mile range and a 0.23 drag coefficient - TESLARATI", "16 April 2023", Constants.RECENT))
        rList.add(RecentModel(R.drawable.img_4, "https://www.benzinga.com/markets/cryptocurrency/23/04/31845622/dogecoin-rises-even-as-bitcoin-ethereum-tumble-analyst-predicts-start-of-full-blown-alt-se","Dogecoin Rises Even As Bitcoin, Ethereum Tumble - Benzinga", "16 April 2023", Constants.RECENT))
        // Breaking List :->
        val bList = ArrayList<RecentModel>()
        bList.add(RecentModel(R.drawable.img_5, "https://www.youtube.com/watch?v=33-wwzBfjLY","Portland's only REI store to close early next year, citing rise in break-ins, thefts - KGW News", "", Constants.BREAKING))
        bList.add(RecentModel(R.drawable.img_6, "https://www.youtube.com/watch?v=CtQKolAmW9c","Jim Cramer shares his take on whether the bulls have won the war on sentiment - CNBC Television", "", Constants.BREAKING))
        bList.add(RecentModel(R.drawable.img_7, "https://www.cbsnews.com/news/davids-bridal-bankrupt-9000-layoffs/","David's Bridal declares bankruptcy, will lay off more than 9,000 workers - CBS News", "", Constants.BREAKING))
        bList.add(RecentModel(R.drawable.img_8, "https://www.reuters.com/business/autos-transportation/chinas-electric-car-drive-led-by-byd-leaves-global-brands-behind-2023-04-17/","China's electric car drive, led by BYD, leaves global brands behind - Reuters", "", Constants.BREAKING))
        bList.add(RecentModel(R.drawable.img_9, "https://www.reuters.com/markets/us/futures-subdued-investors-eye-bank-earnings-fed-cues-2023-04-17/","Wall St ends higher; investors await earnings, Fed cues - Reuters", "", Constants.BREAKING))
        // MainAdapter :->
        val mList = ArrayList<BreakingModel>()
        mList.add(BreakingModel(Constants.RECENT,  "Latest News", rList))
        mList.add(BreakingModel(Constants.RECENT, "For You", pList))
        mList.add(BreakingModel(Constants.BREAKING, "Business", bList))
        mList.add(BreakingModel(Constants.RECENT,  "Recent News", rList))
        ryc(mList)

        //  RecyclerView :->
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.mainRecyclerView.setHasFixedSize(true)

        getNews()
        return binding.root
    }

    private fun getNews(){
        RetrofitInstance.apiInterface.getData("us", "business",1, "dda699352c8648c0a75a1b4222db7cae")
            .enqueue(object : retrofit2.Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val news = response.body()
                    if (news != null){
                        adapter = ApiAdapter(requireContext(), news.articles as ArrayList<Article>)
                        binding.mainRecyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                        Log.d("news", news.toString())
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.d("news", "Error")
                }

            })
    }
    private fun ryc(mainList: ArrayList<BreakingModel>){
        binding.recycle.layoutManager = LinearLayoutManager(requireContext())
        binding.recycle.setHasFixedSize(true)
        myAdapter = MyAdapter(requireContext(), mainList)
        binding.recycle.adapter = myAdapter
    }

    private fun newRyc(mList: ArrayList<SetUpModel>){
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.setHasFixedSize(true)
        thirdAdapter = ThirdAdapter(requireContext(), mList)
        binding.recycler.adapter = thirdAdapter
    }
}