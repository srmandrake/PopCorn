# PopCorn

Movie Info Android App

## OMDb API

http://www.omdbapi.com/

API KEY: 3f9c2cd0


## Create Project

1) Basic Activity

2) Package Name [Reverse Domain Name Notation](https://en.wikipedia.org/wiki/Reverse_domain_name_notation)

3) Change Primary Color

4) Change Accent Color

## MainActivity

1) Set the EditText

	- Hint = Let's Find This Flick

2) Set Screen Margin

	- 16dp as of [Material Design Standards](https://material.io/design/)

3) Add Click Listener to Fab Button

4) Refactor MainActivity Layout

	- CoordinatorLayout to FrameLayout

	- Add id/ to MainActivity main view

## MovieInfoFragment

1) Refactor newInstance
	
	- Change params (amount and name)
	
	- Change ARG_ name everywhere

2) Load fragment in MainActivity -> Fab -> ClickListener

	- SupportFragmentManager... beginTransaction - add() - commit()

		- Hide Fab Button

	- Show Fab Button onBackPressed()

	- Pass <movieName> to Fragment

	- Handle Crash upon onAttach()
	
	- Fragment Color and size

	- Fragment TextView (center)

	- Handle built-in Back button (FragmentManager.addToBackStack())

	(*) Validate <movieName> (Empty() or Blank(), Toast, trim())

3) Create Fragment Layout

## Movie database test
	
1) Get a movie info (from web page)

2) Pass json response to JsonLint.com

## Add Networking Library

1) Add Ion to Gradle (https://github.com/koush/ion)
'''
dependencies {
		com.koushikdutta.ion:ion:2.2.1
}
'''

2) Fields (Title, Year, Released, Plot, Poster)

## Make the call

1) Map TextViews to Json values

2) Load Poster Image into ImageView

3) Info validations
