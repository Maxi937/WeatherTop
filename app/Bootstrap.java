import java.util.List;

import play.*;
import play.jobs.*;
import play.test.*;

import models.*;
import utils.InstrumentUtil;


@OnApplicationStart
public class Bootstrap extends Job
{
    public void doJob()
    {
        Fixtures.loadModels("data.yml");
        InstrumentUtil.loadData("instrumentData.yml");
    }
}