$("#flFoto").change(function() {
	$("#fotoCadastroProduto").children().remove();
  readURL(this);
});

function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function(e) {
    	$("#fotoCadastroProduto").append(`<img src="${e.target.result}" height="100" width="100" />`)
    }

    reader.readAsDataURL(input.files[0]);
  }
}