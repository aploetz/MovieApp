package com.codewithjava21.movieapp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.codewithjava21.movieapp.service.Movie;
import com.codewithjava21.movieapp.service.MovieAppController;
import com.codewithjava21.movieapp.service.MovieByTitleRepository;
import com.codewithjava21.movieapp.service.MovieRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Route;

@Route("")
public class MovieAppMainView extends VerticalLayout {

	private static final long serialVersionUID = -1773398995755184441L;
	
	private TextField queryField = new TextField();
	private RadioButtonGroup<String> queryBy = new RadioButtonGroup<>();
	
	private Button queryButton = new Button();
	private Button imageButton = new Button();
	
	private Image image = new Image();
	
	private Span genre1 = new Span();
	private Span genre2 = new Span();
	private Span genre3 = new Span();

	private TextField movieId = new TextField("ID");
	private TextField releaseDate = new TextField("release date");
	private TextField website = new TextField("website");
	private TextField imdbWebsite = new TextField("IMDB website");
	private TextField imdb = new TextField("IMDB");
	private TextField language = new TextField("original language");
	private TextField budget = new TextField("budget");
	private TextField revenue = new TextField("revenue");
	private TextField voteRating = new TextField("rating");
	private TextField votes = new TextField("total votes");
	
	private Paragraph title = new Paragraph();
	private Paragraph description = new Paragraph();
	private Paragraph year = new Paragraph();
	
	private String imageFile = "noimage";
	private Map<Integer,String> mapGenres = new HashMap<>();
	
	private MovieAppController controller;
	
	public MovieAppMainView(MovieRepository mRepo, MovieByTitleRepository mtRepo) {
		controller = new MovieAppController(mRepo, mtRepo);
		
		add(buildQueryBar());
		add(buildTitle());
		add(year);
		add(buildGenreData());
		
		add(buildImageData());
		add(description);
		add(buildMovieMetaData());
		add(buildFinancialData());
		add(buildRatingData());
		add(buildWebsiteData());
	}
	
	private Component buildQueryBar() {
		HorizontalLayout layout = new HorizontalLayout();
		
		Button queryButton = new Button("Query");
		queryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		Icon search = new Icon(VaadinIcon.SEARCH);
		queryField.setPrefixComponent(search);
		
		layout.add(queryField, queryButton, buildQueryRadio());
		
		queryButton.addClickListener(click -> {
			refreshData();
		});
		
		return layout;
	}
	
	private Component buildTitle() {
		HorizontalLayout layout = new HorizontalLayout();

		title.getStyle()
				.set("font-weight", "bold")
				.set("font-size", "x-large");
		
		layout.add(title);
		
		return layout;
	}
		
	private Component buildQueryRadio() {
		HorizontalLayout layout = new HorizontalLayout();

		queryBy.setLabel("Query by:");
		queryBy.setItems("ID", "Title");
		queryBy.setValue("ID");
		
		queryBy.addValueChangeListener(click -> {
			refreshData();
		});
		
		layout.add(queryBy);
		
		return layout;		
	}
	
	private Component buildGenreData() {
		HorizontalLayout layout = new HorizontalLayout();

		genre1.getElement().getThemeList().add("badge");
		genre1.setVisible(false);
		
		genre2.getElement().getThemeList().add("badge");
		genre2.setVisible(false);
		
		genre3.getElement().getThemeList().add("badge");
		genre3.setVisible(false);

		layout.add(genre1, genre2, genre3);
		
		return layout;
	}

	private Component buildImageData() {
		HorizontalLayout layout = new HorizontalLayout();
		
		image.setSrc(imageFile);
		imageButton.addClickListener(click -> {
			// load new image file
		});
		layout.add(image, imageButton);
		
		return layout;
	}

	private Component buildMovieMetaData() {
		HorizontalLayout layout = new HorizontalLayout();

		movieId.setReadOnly(true);
		imdb.setReadOnly(true);
		language.setReadOnly(true);
		
		layout.add(movieId, imdb, language);
				
		return layout;
	}
	
	private Component buildFinancialData() {
		HorizontalLayout layout = new HorizontalLayout();

		releaseDate.setReadOnly(true);
		budget.setReadOnly(true);
		revenue.setReadOnly(true);
		
		layout.add(releaseDate, budget, revenue);
		

		return layout;
	}
	
	private Component buildRatingData() {
		HorizontalLayout layout = new HorizontalLayout();

		voteRating.setReadOnly(true);
		votes.setReadOnly(true);
		
		layout.add(voteRating, votes);
		
		return layout;
	}
	
	private Component buildWebsiteData() {
		HorizontalLayout layout = new HorizontalLayout();
		
		website.setReadOnly(true);
		imdbWebsite.setReadOnly(true);
		
		layout.add(website, imdbWebsite);
		
		return layout;
	}
	
	private void refreshData() {
	
		try {
			Movie movie = new Movie();
			
			if (queryBy.getValue().equals("ID")) {
				movie = controller.getMovieByMovieId(Integer.parseInt(queryField.getValue())).getBody();
			} else {
				// name
				movie = controller.getMovieByTitle(queryField.getValue()).getBody();
			}
	
			String strTitle = movie.getTitle();
			String strImage = movie.getImage();
			String strDescription = movie.getDescription();
			Integer intMovieId = movie.getMovieId();
			LocalDate ldReleaseDate = movie.getReleaseDate();
			Integer intYear = movie.getYear();
			mapGenres = movie.getGenres();
			String strWebsiteUrl = movie.getWebsite();
			String strImdbId = movie.getImdbId();
			String strLanguage = movie.getOriginalLanguage();
			Long longBudget = movie.getBudget();
			Long longRevenue = movie.getRevenue();
			//Object vector = movie.getVector();
			
			title.setText(strTitle);
			imageFile = strImage;
			description.setText(strDescription);
			
			if (movie.getMovieId() != null) {
				movieId.setValue(intMovieId.toString());
			}
			
			if (movie.getReleaseDate() != null) {
				releaseDate.setValue(ldReleaseDate.toString());
			}
	
			year.setText(intYear.toString());
	
			// process genre "badges"
			int genreCounter = 0;
			genre1.setVisible(false);
			genre2.setVisible(false);
			genre3.setVisible(false);
	
			for (String genre : mapGenres.values()) {
	
				switch (genreCounter) {
				case 0:
					genre1.setText(genre);;
					genre1.setVisible(true);
					break;
				case 1:
					genre2.setText(genre);
					genre2.setVisible(true);
					break;
				case 2:
					genre3.setText(genre);
					genre3.setVisible(true);
					break;
				default:
					break;
				}
	
				genreCounter++;
			}
			
			website.setValue(strWebsiteUrl);
			imdbWebsite.setValue("https://www.imdb.com/title/" + strImdbId);
			imdb.setValue(strImdbId);
			language.setValue(strLanguage);
			
			if (movie.getBudget() != null) {
				budget.setValue(longBudget.toString());
			}
			
			if (movie.getRevenue() != null) {
				revenue.setValue(longRevenue.toString());
			}
			
			if (movie.getVector() != null) {
				voteRating.setValue(movie.getVector().get(5).toString());
				votes.setValue(movie.getVector().get(6).toString());
			}
		} catch (Exception ex) {
			Notification.show("No movie found for those query parameters.",
					5000, Position.TOP_CENTER);
		}
	}
}
