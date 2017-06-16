requirejs.config( {
	"baseUrl": "/static",
	"paths": {
		"jquery":               "libs/jquery/jquery-2.0.3.min",
		"jquery-migrate":       "libs/jquery/jquery-migrate-1.2.1.min",
		"jquery-form":          "libs/jquery-form/jquery-form-20131225.min"
	},
	"shim": {
		"jquery-migrate":       ["jquery"],
		"jquery-form":          ["jquery"]
	}
});