# HCH_Contacto
Aplicación de agenda de contacto para el público en general que permite obtener información general del personal medico,
con la finalidad de acercar a la comunidad chiricana con los servicios ofrecidos por el Hospital Chiriquí.


# Rutas para el server 

Route::get('api/medicos', 'ApiMedicoController@index');
Route::post('api/find', 'ApiMedicoController@getby');
Route::get('api/especialidades','ApiMedicoController@getEspecialidades');

# codigo para Controllador 
class ApiMedicoController extends \BaseController {


	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
        $datos = DB::select("SELECT * FROM medicos");
        
        return Response::json($this->getData_medico($datos));
	}
	
	
	public function getData_medico($datos){
        
        foreach($datos as $medico){

            $especialidad_nombre = EspecialidadMedica::where('id_especialidades_medicas', $medico->id_especialidades_medicas)->first();
            $medico->id_especialidades_medicas =$especialidad_nombre->toArray();

            if(empty($medico->foto)){
                $medico->foto = "default1.png";
            }
            if(!empty($medico->id_nivel)){
                $medico->id_nivel =  Nivel::where('id', $medico->id_nivel)->first()->nivel;
            }else{
                $medico->id_nivel =  'POR DEFINIR';
            }
            if(!empty($medico->id_ubicacion)){
                $medico->id_ubicacion = Ubicacion::where('id', $medico->id_ubicacion)->first()->ubicacion;
            }else{
                $medico->id_ubicacion = 'POR DEFINIR';
            }

        }

        return $datos;
    }

    public function getby(){

       $query = "SELECT * FROM medicos where";
       $bandera = 0;

       if(Request::get('especialidad')){

           $bandera = 1;
           $query = $query. " id_especialidades_medicas ='".Request::get('especialidad')."'";
       }
        if(Request::get('nombre')){

           if($bandera == 1){
               $query = $query. " and concat(`primer_nombre`,' ',`apellido_paterno`)  LIKE '%".Request::get('nombre')."%'";
           }else{
               $bandera = 1;
               $query = $query. " concat(`primer_nombre`,' ',`apellido_paterno`)  LIKE '%".Request::get('nombre')."%'";
           }

        }

        if(Request::get('email')){

        }

        $query = $query. " ";

        if($bandera == 0){
            $query = "SELECT * FROM medicos";
        }

        $datos = DB::select($query);


            return Response::json($this->getData_medico($datos));
        
    }

    public function getEspecialidades(){

	    return Response::json(EspecialidadMedica::all());
    }


	/**
	 * Show the form for creating a new resource.
	 *
	 * @return Response
	 */
	public function create()
	{
		//
	}


	/**
	 * Store a newly created resource in storage.
	 *
	 * @return Response
	 */
	public function store()
	{
		//
	}


	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function show($id)
	{
		//
	}


	/**
	 * Show the form for editing the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function edit($id)
	{
		//
	}


	/**
	 * Update the specified resource in storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function update($id)
	{
		//
	}


	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
		//
	}


}
# sugerencia 
cree el archivo ApiMedicoController.php en el contenedor de Controladores del framework 

# Licencia
Realm Java is published under the Apache 2.0 license.
# Creado por Mel Nielsen 
