/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Hanafi-PC
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@Controller // This means that this class is a Controller
@RequestMapping(path = "/")
public class MeController {

    @Autowired
    private MyRepoOne repohewan;
    @Autowired
    private MyRepoTwo repojenis;
    @Autowired
    private MyRepoThr repotipe;
    
    private int id = 0;

    @GetMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNew(@RequestParam(value = "nama", defaultValue = "null") String nama,
            @RequestParam(value = "jenis", defaultValue = "001") String jenis,
            @RequestParam(value = "tipe", defaultValue = "001") String tipe) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        if (jenis == null || tipe == null || nama == null) {
            return "cant save";
        }
        Hewan n = new Hewan();
        n.setId(1 + id);
        n.setJenis(jenis);
        n.setTipe(tipe);
        n.setNama(nama);
        repohewan.save(n);
        return "<a href='/all'>LIHAT DATA</a>";
    }

    @GetMapping(path = "/delete") // Map ONLY POST Requests
    public @ResponseBody
    String delete(@RequestParam(value = "id") int id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        repohewan.deleteById(id);
        return "<a href='/'>HOME</a>";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    StringBuffer index() {
        StringBuffer isi = new StringBuffer("");
        Iterable<Hewan> systemlist = repohewan.findAll();
        isi.append("<body><table border=1>");
        isi.append("<tr>");
        isi.append("<th>ID</th>");
        isi.append("<th>Nama</th>");
        isi.append("<th>Jenis</th>");
        isi.append("<th>Tipe</th>");
        isi.append("<th>Oprasi</th>");
        isi.append("</tr>");
        for (Hewan i : systemlist) {
            isi.append("<tr>");
            isi.append("<td>" + i.getId() + "</td>");
            isi.append("<td>" + i.getNama() + "</td>");
            isi.append("<td>" + i.getJenis() + "</td>");
            isi.append("<td>" + i.getTipe() + "</td>");
            isi.append("<td><a href='/delete?id=" + i.getId() + "' >Delete</a> | <a href='/edit?id=" + i.getId() + "&nama=" + i.getNama() + "&jenis=" + i.getJenis() + "&tipe=" + i.getTipe() + "' >Edit</a></td>");
            isi.append("</tr>");
        }
        isi.append("</table><a href='/'>Home</a></body>");
        return isi;
    }

    /*
    @GetMapping(path = "/getjsontype")
    public @ResponseBody
    Iterable<Hewan> getAll() {
        return repojoin.getJoin();
    }
    */
    @GetMapping(path = "/")
    public String getForm() {
        id = (int) repohewan.count();
        while (true) {
            if (!repohewan.existsById(1 + id)) {
                break;
            }
            id++;
        }
        return "redirect:/index.html";
    }

    @GetMapping(path = "/jenis")
    public @ResponseBody
    Iterable<Jenis> getJenis() {
        return repojenis.findAll();
    }

    @GetMapping(path = "/tmp")
    public @ResponseBody
    StringBuffer getTemp() {
        StringBuffer a = new StringBuffer("");
        a.append("<body><form action=\"/add\"  method=\"GET\">");
        a.append("<input type=\"text\" id=\"nama\" name=\"nama\"><br>");
        a.append("<p>Jenis Hewan</p>");
        Iterable<Jenis> systemlist = repojenis.findAll();
        for (Jenis i : systemlist) {
            a.append("<input type=\"radio\" id=" + i.getNo() + " name=\"jenis\" value=" + i.getNo() + "><label for=" + i.getNo() + ">" + i.getNama() + "</label><br>");
        }
        a.append("<p>Tipe Hewan</p>");
        Iterable<Tipe> systemlist2 = repotipe.findAll();
        for (Tipe i : systemlist2) {
            a.append("<input type=\"radio\" id=" + i.getNo() + " name=\"tipe\" value=" + i.getNo() + "><label for=" + i.getNo() + ">" + i.getTipe() + "</label><br>");
        }
        a.append("<input type=\"submit\" value=\"SIMPAN\"></form></body>");

        return a;
    }

    @GetMapping(path = "/edit")
    public @ResponseBody
    StringBuffer getEdit(@RequestParam(value = "id") int id, @RequestParam(value = "nama") String nama, @RequestParam(value = "jenis") String jenis, @RequestParam(value = "tipe") String tipe) {
        StringBuffer a = new StringBuffer("");
        a.append("<body><form action=\"/edit2\"  method=\"GET\">");
        a.append("<input type=\"hidden\" id=\"id\" name=\"id\" value='" + id + "'><br>");
        a.append("<input type=\"text\" id=\"nama\" name=\"nama\" value='" + nama + "'><br>");
        a.append("<p>Jenis Hewan</p>");
        Iterable<Jenis> systemlist = repojenis.findAll();
        for (Jenis i : systemlist) {
            if (i.getNo().equalsIgnoreCase(jenis)) {
                a.append("<input type=\"radio\" id=" + i.getNo() + " name=\"jenis\" value=" + i.getNo() + " checked><label for=" + i.getNo() + ">" + i.getNama() + "</label><br>");
            } else {
                a.append("<input type=\"radio\" id=" + i.getNo() + " name=\"jenis\" value=" + i.getNo() + "><label for=" + i.getNo() + ">" + i.getNama() + "</label><br>");
            }
        }
        a.append("<p>Tipe Hewan</p>");
        Iterable<Tipe> systemlist2 = repotipe.findAll();
        for (Tipe i : systemlist2) {
            if (i.getNo().equalsIgnoreCase(tipe)) {
                a.append("<input type=\"radio\" id=" + i.getNo() + " name=\"tipe\" value=" + i.getNo() + " checked><label for=" + i.getNo() + ">" + i.getTipe() + "</label><br>");

            } else {
                a.append("<input type=\"radio\" id=" + i.getNo() + " name=\"tipe\" value=" + i.getNo() + "><label for=" + i.getNo() + ">" + i.getTipe() + "</label><br>");

            }
        }
        a.append("<input type=\"submit\" value=\"SIMPAN\"></form></body>");

        return a;
    }

    @GetMapping(path = "/edit2") // Map ONLY POST Requests
    public @ResponseBody
    String getEdit2(@RequestParam(value = "id") int id,
            @RequestParam(value = "nama", defaultValue = "null") String nama,
            @RequestParam(value = "jenis", defaultValue = "001") String jenis,
            @RequestParam(value = "tipe", defaultValue = "001") String tipe) {
        Hewan n = new Hewan();
        n.setId(id);
        n.setJenis(jenis);
        n.setTipe(tipe);
        n.setNama(nama);
        repohewan.save(n);
        return "<a href='/all'>LIHAT DATA</a>";
    }
}
