package br.com.vrosendo.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
import org.thymeleaf.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.vrosendo.domain.Group;
import br.com.vrosendo.domain.Media;
import br.com.vrosendo.domain.Movie;
import br.com.vrosendo.dtos.OmdbDto;
import br.com.vrosendo.services.MediaService;
import rx.Observable;
import rx.schedulers.Schedulers;

@Service
public class MediaServiceImpl implements MediaService{

	/*@Override
	public void listMediaByUser(Long id, DeferredResult<List<Group>> deferredResult) {
	
		List<Group> resultado = new ArrayList<Group>();
		
		Observable<Group> allMediaObservable = Observable.from(fetchAllMediaAvailable()).observeOn(Schedulers.newThread());
		Observable<Group> favoriteByUserObservable = Observable.just(fetchAllFavoriteByUser());
		Observable<Group> recomendedByUserObservable = Observable.just(fetchAllRecomendedByUser());
		
		Observable.concat(allMediaObservable, favoriteByUserObservable, recomendedByUserObservable).observeOn(Schedulers.newThread()).flatMap((it) -> {
			return Observable.just(it).subscribeOn(Schedulers.computation()).map((x) -> {
				x.getItems().forEach((m) -> {
					m.setImage(fetchImagePoster(m.getName()));
				});
				return x;
			});
		}).subscribe((it) -> {
			resultado.add(it);
		}, (x) -> {
		}, () -> {
			deferredResult.setResult(resultado);
		});
		
	}*/
	
	@Override
	public void listMediaByUser(Long id, DeferredResult<List<Group>> deferredResult) {
	
		List<Group> resultado = new ArrayList<Group>();
		
		/*******
		 * Future Task 1
		 *******/
		
		FutureTask<List<Group>> futureTask = new FutureTask<List<Group>>(() -> {
            return fetchAllMediaAvailable();
        });

	    Observable<List<Group>> from = Observable.from(futureTask);
	    
        Schedulers.computation().createWorker().schedule(() -> {
        	futureTask.run();
        });
        
        Observable<Group> flatMap = from.flatMap((x) -> {
        	return Observable.from(x).subscribeOn(Schedulers.computation());
		});
        
        /*******
		 * Future Task 2
		 *******/
        
        FutureTask<Group> futureTask2 = new FutureTask<Group>(() -> {
            return fetchAllFavoriteByUser();
        });

	    Observable<Group> from2 = Observable.from(futureTask2);
	    
        Schedulers.computation().createWorker().schedule(() -> {
        	futureTask2.run();
        });
        
        /*******
		 * Future Task 3
		 *******/
        
        FutureTask<Group> futureTask3 = new FutureTask<Group>(() -> {
            return fetchAllRecomendedByUser();
        });

	    Observable<Group> from3 = Observable.from(futureTask3);
	    
        Schedulers.computation().createWorker().schedule(() -> {
        	futureTask3.run();
        });
        
        /*******
		 * Main Task
		 *******/
        
        Observable<Group> allGroupsObservable = Observable.concat(flatMap, from2, from3).flatMap((it) -> {
			return Observable.just(it).subscribeOn(Schedulers.computation()).map((x) -> {
				x.getItems().forEach((m) -> {
					m.setImage(fetchImagePoster(m.getName()));
				});
				return x;
			});
		});
        
        allGroupsObservable.subscribe((it) -> {
        	resultado.add(it);
		}, (x) -> {
			System.out.println(x);
		}, () -> {
			deferredResult.setResult(resultado);
		});
		
	}
		
	private List<Group> fetchAllMediaAvailable(){
		
		/*try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		System.out.println("INIT FETCHING ALL MEDIA - Thread: " + Thread.currentThread().getName());
		
		List<Group> grupos = new ArrayList<>();
		
		List<Media> list1 = new ArrayList<>();
		
		Media movie1 = new Movie();
		movie1.setName("Jurassic Park");
		movie1.setDescription("A Movie About Dinos...");
		//movie1.setImage(fetchImagePoster(movie1.getName()));
		movie1.setYear(1993L);
		
		Media movie2 = new Movie();
		movie2.setName("The Terminator");
		movie2.setDescription("A Movie About a Robot coming from the future...");
		//movie2.setImage(fetchImagePoster(movie2.getName()));
		movie2.setYear(1984L);
		
		Media movie3 = new Movie();
		movie3.setName("The Matrix");
		movie3.setDescription("A Movie About Another reality where the machine domains...");
		//movie3.setImage(fetchImagePoster(movie3.getName()));
		movie3.setYear(1999L);
		
		list1.add(movie1);
		list1.add(movie2);
		list1.add(movie3);
		
		grupos.add(new Group("Sci-fi", list1));
		
		List<Media> list2 = new ArrayList<>();
		
		Media movie4 = new Movie();
		movie4.setName("Mission Imposible");
		movie4.setDescription("A Movie About Agents...");
		//movie4.setImage("https://images-na.ssl-images-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_UX182_CR0,0,182,268_AL_.jpg");
		movie4.setYear(1996L);
		
		Media movie5 = new Movie();
		movie5.setName("Lethal Weapon");
		movie5.setDescription("A Movie About Crimes...");
		//movie5.setImage("https://images-na.ssl-images-amazon.com/images/M/MV5BZTllNWNlZjctMWQwMS00ZDc3LTg5ZjMtNzhmNzhjMmVhYTFlXkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_UY268_CR0,0,182,268_AL_.jpg");
		movie5.setYear(1989L);
		
		Media movie6 = new Movie();
		movie6.setName("Die Hard");
		movie6.setDescription("A Movie About Polices...");
		//movie6.setImage("http://ia.media-imdb.com/images/M/MV5BYmY2ZGEwMTYtNjBmZS00OGE4LWEyMmUtNjAwMWUxZjVmZTRiXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg");
		movie6.setYear(1988L);
		
		list2.add(movie4);
		list2.add(movie5);
		list2.add(movie6);
		
		grupos.add(new Group("Action", list2));
		
		return grupos;
		
	}
	
	private Group fetchAllFavoriteByUser(){
		
		/*try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		System.out.println("INIT FETCHING FAVORITES - Thread: " + Thread.currentThread().getName());
		
		List<Media> list = new ArrayList<>();
		
		Media movie1 = new Movie();
		movie1.setName("Lords of the ring");
		movie1.setDescription("A Movie of Fantasy where the Humans, Elfs, Dwarves, Ogres battle for control of the middle-earth...");
		//movie1.setImage("https://images-na.ssl-images-amazon.com/images/M/MV5BNTEyMjAwMDU1OV5BMl5BanBnXkFtZTcwNDQyNTkxMw@@._V1_UY268_CR0,0,182,268_AL_.jpg");
		movie1.setYear(2002L);
		
		list.add(movie1);
		
		return new Group("Favorite", list);
		
	}
	
	private Group fetchAllRecomendedByUser(){
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("INIT FETCHING RECOMENDED - Thread: " + Thread.currentThread().getName());
		
		List<Media> list = new ArrayList<>();
		
		Media movie1 = new Movie();
		movie1.setName("Harry Potter and the Sorcerer Stone");
		movie1.setDescription("A Movie About Wizards...");
		//movie1.setImage("https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNTM5NDkzNV5BMl5BanBnXkFtZTYwODQ4MzY5._V1_UY268_CR6,0,182,268_AL_.jpg");
		movie1.setYear(1997L);
		
		Media movie2 = new Movie();
		movie2.setName("The Hobbit");
		movie2.setDescription("A Movie About a Hobbit...");
		//movie2.setImage("https://images-na.ssl-images-amazon.com/images/M/MV5BMTcwNTE4MTUxMl5BMl5BanBnXkFtZTcwMDIyODM4OA@@._V1_UX182_CR0,0,182,268_AL_.jpg");
		movie2.setYear(2012L);
		
		list.add(movie1);
		list.add(movie2);
		
		return new Group("Recomended", list);
		
	}	
	
	private String fetchImagePoster(String name){
		
		
		System.out.println("INIT FETCHING IMAGE " + name + " - Thread: " + Thread.currentThread().getName());
		
		String imageUrl = "http://ia.media-imdb.com/images/M/MV5BMTg1MjQyMDQ4MV5BMl5BanBnXkFtZTgwMTg3ODA4MjE@._V1_SX300.jpg";
		
		try{
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			HttpGet getRequest = new HttpGet("http://www.omdbapi.com/?t=" + URLEncoder.encode(name, "utf-8"));
			
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);
			
			String jSonStrReturn = "";
			
			if (response.getStatusLine().getStatusCode() == 200) {
				
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

				String output;
				
				while ((output = br.readLine()) != null) {
					jSonStrReturn += output;
				}
				
				if(!StringUtils.isEmpty(jSonStrReturn)){
					
					Gson gson = new GsonBuilder().create();
					OmdbDto fromJson = gson.fromJson(jSonStrReturn, OmdbDto.class);
					
					if(fromJson != null && !StringUtils.isEmpty(fromJson.getPoster()) && !fromJson.getPoster().equals("N/A")){
						imageUrl = fromJson.getPoster();
					}
				}

			}
			
			httpClient.getConnectionManager().shutdown();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("END FETCHING IMAGE " + name + " URL=" + imageUrl);
		
		return imageUrl;
		
	}
	
}
