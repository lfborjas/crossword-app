# Crossword App

Learning Clojurescript and React by building a little interactive crossword filler.


## Development

*Note:* I'm using Emacs 25 and the latest CIDER, which ships with a `cider-jack-in-cljs` that connects to a figwheel-main nREPL _and_ gets compilation going, so I end up with a development flow that has an in-editor REPL _and_ live reloading of the HTML:

![image](https://user-images.githubusercontent.com/82133/47980743-072eef00-e097-11e8-9bc9-a021d9cf61b4.png)


To get an interactive development environment run:

    lein fig:build

This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

	lein clean

To create a production build run:

	lein clean
	lein fig:min


## References

* https://www.w3schools.com/graphics/svg_rect.asp
* https://www.w3schools.com/graphics/tryit.asp?filename=trysvg_text
* https://stackoverflow.com/questions/6725288/svg-text-inside-rect
* https://figwheel.org/docs/emacs.html
* https://developer.mozilla.org/en-US/docs/Web/SVG/Tutorial/Getting_Started
* https://developer.mozilla.org/en-US/docs/Web/SVG/Element/foreignObject
* https://github.com/reagent-project/reagent/blob/master/doc/UsingSquareBracketsInsteadOfParens.md
* https://www.w3.org/Graphics/SVG/IG/resources/svgprimer.html#coordinate
* https://developer.mozilla.org/en-US/docs/Web/SVG/Tutorial/SVG_In_HTML_Introduction
* http://reagent-project.github.io/
* 
