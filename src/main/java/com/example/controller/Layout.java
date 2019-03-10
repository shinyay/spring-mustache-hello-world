package com.example.controller;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.IOException;
import java.io.Writer;

public class Layout implements Mustache.Lambda {

    String body;

    @Override
    public void execute(Template.Fragment fragment, Writer writer) throws IOException {
        body = fragment.execute();
    }
}
