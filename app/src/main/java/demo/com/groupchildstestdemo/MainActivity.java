package demo.com.groupchildstestdemo;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import demo.com.groupchildstestdemo.adapter.GroupChildAdapter;
import demo.com.groupchildstestdemo.model.GroupChildsModel;
import demo.com.groupchildstestdemo.model.GroupChildsModelResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView rvList;
  private GroupChildAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    rvList = findViewById(R.id.recycleview);
    adapter = new GroupChildAdapter(this);
    rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    rvList.setAdapter(adapter);
    adapter.setData(getList());
  }


  public ArrayList<GroupChildsModel> getList() {



    Gson g = new Gson();
    String jsonString="{\n"
        + "  \"sort\": [\n"
        + "    {\n"
        + "      \"Mid\": 1,\n"
        + "      \"Tid\": 123456797,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 2,\n"
        + "      \"Tid\": 123456794,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 3,\n"
        + "      \"Tid\": 123456791,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 3,\n"
        + "      \"Tid\": 123456791,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 1,\n"
        + "      \"Tid\": 123456797,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 2,\n"
        + "      \"Tid\": 123456794,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 1,\n"
        + "      \"Tid\": 123456797,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 3,\n"
        + "      \"Tid\": 123456791,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 1,\n"
        + "      \"Tid\": 123456795,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 1,\n"
        + "      \"Tid\": 123456795,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    },\n"
        + "    {\n"
        + "      \"Mid\": 3,\n"
        + "      \"Tid\": 123456791,\n"
        + "      \"amount\": 12.32,\n"
        + "      \"narration\": 11234684654\n"
        + "    }\n"
        + "  ]\n"
        + "}";
    GroupChildsModelResult groupChildsModelResult = g.fromJson(jsonString, GroupChildsModelResult.class);

    ArrayList<GroupChildsModel> models = new ArrayList<>();








    if (groupChildsModelResult!=null && groupChildsModelResult.getSort().size()>0){


     List<GroupChildsModel> groupChildsModelList=groupChildsModelResult.getSort();



      Iterator<GroupChildsModel> groupChildsModelIterator = groupChildsModelList.iterator();


      while (groupChildsModelIterator.hasNext()){   //

        GroupChildsModel groupChildsModel = groupChildsModelIterator.next();

        if (groupChildsModel!=null){

          Iterator<GroupChildsModel> adapterGroupChildsModelIterator = models.iterator();

          boolean isMidItemFound=false;




          while (adapterGroupChildsModelIterator.hasNext()){  ///

            GroupChildsModel adapterGroupChildsModel = adapterGroupChildsModelIterator.next();

            if (adapterGroupChildsModel!=null){

              if (adapterGroupChildsModel.getMid()==groupChildsModel.getMid()){
                isMidItemFound=true;



                if (adapterGroupChildsModel!=null && adapterGroupChildsModel.childsModels!=null) {

                  Iterator<GroupChildsModel> adapterTidModelIterator = adapterGroupChildsModel.childsModels
                      .iterator();

                  boolean isTidItemFound = false;

                  while (adapterTidModelIterator.hasNext()) {  ///

                    GroupChildsModel adapterGroupChildsTidModel = adapterTidModelIterator
                        .next();


                    if (adapterGroupChildsTidModel.getTid()==groupChildsModel.getTid()){

                      isTidItemFound = true;

                      GroupChildsModel modelAmt = new GroupChildsModel();
                      modelAmt.setName(""+groupChildsModel.getNarration()+"    "+
                          groupChildsModel.getAmount());
                      modelAmt.setLevel(3);
                      modelAmt.state = GroupChildsModel.STATE.CLOSED;
                      modelAmt.setMid(groupChildsModel.getMid());
                      modelAmt.setTid(groupChildsModel.getTid());



                      if (adapterGroupChildsTidModel.childsModels==null)
                        adapterGroupChildsTidModel.childsModels= new ArrayList<>();

                      adapterGroupChildsTidModel.childsModels.add(modelAmt);

                      break;


                    }




                  }


                  if (!isTidItemFound){

                    GroupChildsModel modelTid = new GroupChildsModel();
                    modelTid.setName(""+groupChildsModel.getTid());
                    modelTid.setLevel(2);
                    modelTid.state = GroupChildsModel.STATE.CLOSED;
                    modelTid.setMid(groupChildsModel.getMid());
                    modelTid.setTid(groupChildsModel.getTid());




                    GroupChildsModel modelAmt = new GroupChildsModel();
                    modelAmt.setName(""+groupChildsModel.getNarration()+"    "+
                        groupChildsModel.getAmount());
                    modelAmt.setLevel(3);
                    modelAmt.state = GroupChildsModel.STATE.CLOSED;
                    modelAmt.setMid(groupChildsModel.getMid());
                    modelAmt.setTid(groupChildsModel.getTid());



                    if (modelTid.childsModels==null)
                      modelTid.childsModels= new ArrayList<>();

                    modelTid.childsModels.add(modelAmt);


                    adapterGroupChildsModel.childsModels.add(modelTid);




                  }



                }







                break;

              }


            }


          }

          if (isMidItemFound){




          }else {


            GroupChildsModel modelMid = new GroupChildsModel();
            modelMid.setName(""+groupChildsModel.getMid());
            modelMid.setLevel(1);
            modelMid.state = GroupChildsModel.STATE.CLOSED;
            modelMid.setMid(groupChildsModel.getMid());





            GroupChildsModel modelTid = new GroupChildsModel();
            modelTid.setName(""+groupChildsModel.getTid());
            modelTid.setLevel(2);
            modelTid.state = GroupChildsModel.STATE.CLOSED;
            modelTid.setMid(groupChildsModel.getMid());
            modelTid.setTid(groupChildsModel.getTid());




            GroupChildsModel modelAmt = new GroupChildsModel();
            modelAmt.setName(""+groupChildsModel.getNarration()+"    "+
                groupChildsModel.getAmount());
            modelAmt.setLevel(3);
            modelAmt.state = GroupChildsModel.STATE.CLOSED;
            modelAmt.setMid(groupChildsModel.getMid());
            modelAmt.setTid(groupChildsModel.getTid());



            if (modelTid.childsModels==null)
              modelTid.childsModels= new ArrayList<>();

            modelTid.childsModels.add(modelAmt);



            if (modelMid.childsModels==null)
              modelMid.childsModels= new ArrayList<>();

            modelMid.childsModels.add(modelTid);



            models.add(modelMid);

          }





        }


      }




    }




    return models;
  }


}
