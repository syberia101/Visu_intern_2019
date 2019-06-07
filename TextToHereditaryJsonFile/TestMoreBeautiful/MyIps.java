package TextToHereditaryJsonFile.TestMoreBeautiful;

/**
 * Created by c1972519 on 6/5/2019.
 */
public class MyIps {
    String line;
    String ipSrc;
    String ipDest;
    String port;

    public MyIps(String l){
        line = l;
        setPositionSrc(line);
        setPositionDest(line);
        setPositionPort(line);
    }

    public void setPositionSrc(String line){
        int positionSrc = line.indexOf("src=");
        int positionDst = line.indexOf("dst=");

        ipSrc = line.substring(positionSrc + 4, positionDst - 1);
    }

    public void setPositionDest(String line){
        int positionDst = line.indexOf("dst=");
        int positionSrcPort = line.indexOf("src_port=");

        ipDest = line.substring(positionDst + 4, positionSrcPort - 1);
    }

    public void setPositionPort(String line){
        int positionDstPort = line.indexOf("dst_port=");
        int positionSrcXlated = line.indexOf("src-xlated");

        port = line.substring(positionDstPort + 9, positionSrcXlated - 1);
    }
}
