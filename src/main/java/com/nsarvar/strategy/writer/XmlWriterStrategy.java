package com.nsarvar.strategy.writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.nsarvar.payload.Athlete;
import com.nsarvar.payload.Result;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlWriterStrategy implements WriterStrategy {
    @Override
    public String writeToFile(List<Athlete> athletes, String outputFilePath) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        String resultFile = outputFilePath + "decathlon-results.xml";
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("competition");
            doc.appendChild(rootElement);

            athletes.forEach(athlete -> appendAthleteElements(doc, rootElement, athlete));

            try (FileOutputStream output =
                         new FileOutputStream(resultFile)) {
                writeXml(doc, output);
            } catch (IOException | TransformerException e) {
                e.printStackTrace();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return resultFile;
    }

    private void appendAthleteElements(Document doc, Element rootElement, Athlete athlete) {
        Element athleteEl = doc.createElement("athlete");
        Element name = doc.createElement("name");
        name.setTextContent(athlete.getName());
        Element totalScore = doc.createElement("totalScore");
        totalScore.setTextContent(athlete.getTotalScore().toString());
        Element rank = doc.createElement("rank");
        rank.setTextContent(athlete.getRank());

        rootElement.appendChild(athleteEl);
        athleteEl.appendChild(name);
        athleteEl.appendChild(rank);

        athlete.getResults().forEach(result -> appendResultElements(result, doc, athleteEl));

        athleteEl.appendChild(totalScore);
    }

    private void appendResultElements(Result result, Document doc, Element parent) {
        Element el = doc.createElement(result.getEvent().name());
        el.setTextContent(result.getScore());
        parent.appendChild(el);
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
