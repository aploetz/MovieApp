package com.codewithjava21.movieapp;

import com.codewithjava21.movieapp.service.MovieAppController;
import com.codewithjava21.movieapp.service.MovieAppRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;

public class MovieAppMainView extends VerticalLayout {

	private static final long serialVersionUID = -1773398995755184441L;
	
	private TextField queryField = new TextField("query");
	private RadioButtonGroup<String> queryBy = new RadioButtonGroup<>();
	private Button queryButton = new Button();
	private Image image = new Image();
	private Button imageButton = new Button();
	private TextField movieId = new TextField("ID");
	private TextField releaseDate = new TextField("release date");
	private TextField year = new TextField("year");
	private TextField generes = new TextField("genres");
	private TextField website = new TextField("website");
	private TextField imdb = new TextField("IMDB");
	private TextField langauge = new TextField("original language");
	private TextField budget = new TextField("budget");
	private TextField revenue = new TextField("revenue");
	private TextField voteRating = new TextField("rating");
	private TextField votes = new TextField("total votes");
	
	private String title;
	
	private MovieAppController controller;
	
	public MovieAppMainView(MovieAppRepository repo) {
		controller = new MovieAppController(repo);
		
		add(buildQueryBar());
		
		if (!title.isEmpty()) {
			add(new H1(title));
		}
		
		add()
	}
	
	private Component buildQueryBar() {
		HorizontalLayout layout = new HorizontalLayout();
		
		Button queryButton = new Button("Query");
		queryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		layout.add(queryField, queryButton, buildQueryRadio());
		
		queryButton.addClickListener(click -> {
			refreshData();
		});
		
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
	
	private void refreshData() {
		
	}
}
